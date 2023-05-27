package com.wjh.xss_back.web;

import com.alibaba.fastjson2.JSON;
import com.wjh.xss_back.beans.*;
import com.wjh.xss_back.p2j.Result;
import com.wjh.xss_back.request.Model;
import com.wjh.xss_back.kafka.MessageSender;
import com.wjh.xss_back.response.FileDetectResponse;
import com.wjh.xss_back.service.*;
import com.wjh.xss_back.uitl.OssUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangjunhong
 * @description FileDetectController
 * @since 2023/3/31 16:22
 */
@RestController
@RequestMapping("/file")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@Slf4j
public class FileDetectController {
    private String FILE_DIRECTORY = "/xss-back/csv";
    private String path = "";
    public List<Result> list = null;
    @Autowired
    private FileUploadRecorderService fileUploadRecorderService;
    @Autowired
    private FileDetectRecorderService fileDetectRecorderService;
    @Autowired
    private DetectRecorderService detectRecorderService;
    @Autowired
    private DetectResultService detectResultService;
    @Autowired
    private FileService fileService;
    @Autowired
    private TextService textService;
    @Autowired
    private MessageSender messagesender;
    private String fileId;

    {
        File file = new File(FILE_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @PostMapping("upload")
    public void upload_csv(@RequestPart("file") MultipartFile file) {
        //获取文件名
        String[] names = file.getOriginalFilename().split("[.]");
        System.out.println(names.length);
        System.out.println(file.getOriginalFilename());
        String file_name = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "." + names[names.length - 1];
        path = FILE_DIRECTORY + "/" + file_name;
        log.info("接收到前端上传的文件：" + path);
        if (!file.isEmpty()) {
            try {
                OssUpload.upload(file_name, file.getInputStream());
                file.transferTo(new File(path));
                log.info("文件保存成功");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //记录file
        com.wjh.xss_back.beans.File entity1 = new com.wjh.xss_back.beans.File();
        entity1.setFileName(file_name);
        entity1.setFileSize(file.getSize());
        entity1.setRecorderNum(null);
        fileId = UUID.randomUUID().toString();
        entity1.setId(fileId);
        fileService.save(entity1);

        // 记录file_upload_recorder
        FileUploadRecorder entity = new FileUploadRecorder();
        entity.setUploadDate(new Date());
        entity.setSrcFileName(file.getOriginalFilename());
        entity.setFileId(fileId);
        entity.setId(UUID.randomUUID().toString());
        fileUploadRecorderService.save(entity);
    }

    public synchronized void notify_() {
        notify();
    }

    @PostMapping("/predict")
    public synchronized List<FileDetectResponse> predict(@RequestBody Model model) {
        log.error("predict被调用");
        List<String> texts = new ArrayList<>();
        List<DetectRecorder> detectRecorders = new ArrayList<>();
        List<Text> textList = new ArrayList<>();
        List<FileDetectResponse> fileDetectResponses = new ArrayList<>();
        try {
            String str = null;
            // 读取文件信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while ((str = reader.readLine()) != null) {
                texts.add(str);
            }
            // 记录file_detect_recorder part1
            FileDetectRecorder fileDetectRecorder = new FileDetectRecorder();
            String fileDetectRecorderId = UUID.randomUUID().toString();
            fileDetectRecorder.setUsername(model.getUsername());
            fileDetectRecorder.setId(fileDetectRecorderId);
            fileDetectRecorder.setStartTime(new Date());
            fileDetectRecorder.setUseModel(model.getName());
            fileDetectRecorder.setFileId(fileId);
            fileDetectRecorderService.save(fileDetectRecorder);
            for (String text : texts) {
                Text t = new Text();
                String textId = UUID.randomUUID().toString();
                t.setId(textId);
                t.setUrl(text);
                t.setFileId(fileId);
                DetectRecorder detectRecorder = new DetectRecorder();
                String detectRecorderId = UUID.randomUUID().toString();
                detectRecorder.setUsername(model.getUsername());
                detectRecorder.setId(detectRecorderId);
                detectRecorder.setTextId(textId);
                detectRecorder.setModel(model.getName());
                detectRecorder.setStartDate(new Date());
                FileDetectResponse response = new FileDetectResponse();
                response.setUrl(text);
                response.setModel(model.getName());
                response.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                textList.add(t);
                detectRecorders.add(detectRecorder);
                fileDetectResponses.add(response);
            }
            log.error("开始保存textList" + textList.size());
            //保存text记录
            textService.saveBatch(textList);
            log.error("保存textList结束");
            //记录text_detect_recorder part1
            log.error("开始保存detectRecorders");
            detectRecorderService.saveBatch(detectRecorders);
            log.error("保存detectRecorders结束");
            Map map = new HashMap<String, String>();
            // 读取模型信息
            map.put("model", model.getName());
            map.put("message", JSON.toJSONString(texts));
            messagesender.sendMessage("file", JSON.toJSONString(map));
            wait(120000);
            // 记录file_detect_recorder part2
            fileDetectRecorder.setFinishTime(new Date());
            log.error("消息已发送");
            fileDetectRecorderService.updateById(fileDetectRecorder);
            List<DetectResult> detectResults = new ArrayList<>();
            log.error("detectRecorders.size() = " + (detectRecorders.size()));
            log.error("list.size() = " + (list.size()));
            for (int i = 0; i < list.size(); i++) {
                DetectResult detectResult = new DetectResult();
                detectResult.setResult(list.get(i).getResult());
                detectResult.setDetectRecorderId(detectRecorders.get(i).getId());
                detectResult.setKeyword(list.get(i).getKeyword());
                detectResults.add(detectResult);
                detectRecorders.get(i).setFinishDate(new Date());
                fileDetectResponses.get(i).setLabel(list.get(i).getResult());
                fileDetectResponses.get(i).setKeyword(list.get(i).getKeyword());
                fileDetectResponses.get(i).setIndex(i + 1);
            }
            detectResultService.saveBatch(detectResults);
            //记录text_detect_recorder part2
            detectRecorderService.updateBatchById(detectRecorders);
            log.error(list.get(0).toString());
            return fileDetectResponses;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
            // TODO检测失败
        } finally {
            list = null;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
