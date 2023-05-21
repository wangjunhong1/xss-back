package com.wjh.xss_back.web;

import com.alibaba.fastjson2.JSON;
import com.wjh.xss_back.beans.DetectRecorder;
import com.wjh.xss_back.beans.DetectResult;
import com.wjh.xss_back.beans.Text;
import com.wjh.xss_back.kafka.MessageSender;
import com.wjh.xss_back.p2j.Result;
import com.wjh.xss_back.request.ModelText;
import com.wjh.xss_back.response.KeywordCount;
import com.wjh.xss_back.response.TextDetectResponse;
import com.wjh.xss_back.service.DetectRecorderService;
import com.wjh.xss_back.service.DetectResultService;
import com.wjh.xss_back.service.TextService;
import com.wjh.xss_back.uitl.TimeDiff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangjunhong
 * @description TextDetectController
 * @since 2023/4/15 18:08
 */
@RestController
@RequestMapping("/text")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@Slf4j
public class TextDetectController {
    @Autowired
    private MessageSender messageSender;

    public List<Result> list = null;

    @Autowired
    private TextService textService;
    @Autowired
    private DetectRecorderService detectRecorderService;
    @Autowired
    private DetectResultService detectResultService;

    private Map map = new HashMap<String, String>();

    public synchronized void notify_() {
        notify();
    }

    @PostMapping("/predict")
    public TextDetectResponse predict(@RequestBody ModelText modelText) {
        String text = modelText.getText();
        String[] texts = {text};
        map.put("message", JSON.toJSONString(texts));
        Text t = saveTexts(text);
        TextDetectResponse textDetectResponse = new TextDetectResponse();
        com.wjh.xss_back.response.DetectResult detectResult = new com.wjh.xss_back.response.DetectResult();
        Date begin = new Date();
        detectResult.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin));
        List<com.wjh.xss_back.response.DetectResult.Res> model_res = new ArrayList<>();
        List<String> keywords = new ArrayList<>();
        String username = modelText.getUsername();
        try {
            DetectResult svm = predict_svm(t, username);
            DetectResult k_means = predict_k_means(t, username);
            DetectResult decision_tree = predict_decision_tree(t, username);
            DetectResult logistic_regression = predict_random_forest(t, username);
            DetectResult naive_bayes = predict_naive_bayes(t, username);
            model_res.add(new com.wjh.xss_back.response.DetectResult.Res("svm", svm.getResult()));
            model_res.add(new com.wjh.xss_back.response.DetectResult.Res("decision_tree", decision_tree.getResult()));
            model_res.add(new com.wjh.xss_back.response.DetectResult.Res("k-means", k_means.getResult()));
            model_res.add(new com.wjh.xss_back.response.DetectResult.Res("random_forest", logistic_regression.getResult()));
            model_res.add(new com.wjh.xss_back.response.DetectResult.Res("naive_bayes", naive_bayes.getResult()));
            keywords.addAll(Arrays.asList(svm.getKeyword().replaceAll("[\\[\\]',]", "").split("\\s+")));
            keywords.addAll(Arrays.asList(k_means.getKeyword().replaceAll("[\\[\\]',]", "").split("\\s+")));
            keywords.addAll(Arrays.asList(decision_tree.getKeyword().replaceAll("[\\[\\]',]", "").split("\\s+")));
            keywords.addAll(Arrays.asList(logistic_regression.getKeyword().replaceAll("[\\[\\]',]", "").split("\\s+")));
            keywords.addAll(Arrays.asList(naive_bayes.getKeyword().replaceAll("[\\[\\]',]", "").split("\\s+")));
            log.error(keywords.toString());
        } catch (InterruptedException e) {
            // TODO 错误了
        }
        Map<String, Integer> keyword_ = new HashMap<>();
        List<KeywordCount> keywordCounts = new ArrayList<>();
        for (String keyword : keywords) {
            keyword_.put(keyword, 0);
        }
        for (String s : keyword_.keySet()) {
            keywordCounts.add(new KeywordCount(s, countSubstring(text.toLowerCase(), s)));
        }
        log.error(keyword_.toString());
        detectResult.setModel_res(model_res);
        detectResult.setUse_time(TimeDiff.getTimeDiff(new Date(), begin));
        log.error("text-detect finish.");
        log.error(detectResult.getModel_res().get(0).toString());
        textDetectResponse.setDetectResult(detectResult);
        textDetectResponse.setKeywordCounts(keywordCounts);
        return textDetectResponse;
    }

    private int countSubstring(String original, String substring) {
        int count = 0;
        String[] words = original.split(substring);
        count = words.length - 1;
        return count;
    }

    private synchronized DetectResult predict_svm(Text t, String username) throws InterruptedException {
        DetectRecorder detectRecorder = new DetectRecorder();
        detectRecorder.setUsername(username);
        detectRecorder.setId(UUID.randomUUID().toString());
        detectRecorder.setTextId(t.getId());
        detectRecorder.setModel("svm");
        detectRecorder.setStartDate(new Date());
        detectRecorderService.save(detectRecorder);
        // svm
        map.put("model", "svm");
        messageSender.sendMessage("text", JSON.toJSONString(map));
        wait(120000);
        DetectResult detectResult = new DetectResult();
        detectResult.setDetectRecorderId(detectRecorder.getId());
        detectResult.setResult(list.get(0).getResult());
        detectResult.setKeyword(list.get(0).getKeyword());
        detectRecorder.setFinishDate(new Date());
        detectResultService.save(detectResult);
        detectRecorderService.updateById(detectRecorder);
        return detectResult;
    }

    private synchronized DetectResult predict_k_means(Text t, String username) throws InterruptedException {
        DetectRecorder detectRecorder = new DetectRecorder();
        detectRecorder.setUsername(username);
        detectRecorder.setId(UUID.randomUUID().toString());
        detectRecorder.setTextId(t.getId());
        detectRecorder.setModel("k-means");
        detectRecorder.setStartDate(new Date());
        detectRecorderService.save(detectRecorder);
        // svm
        map.put("model", "k-means");
        messageSender.sendMessage("text", JSON.toJSONString(map));
        wait(120000);
        DetectResult detectResult = new DetectResult();
        detectResult.setDetectRecorderId(detectRecorder.getId());
        detectResult.setResult(list.get(0).getResult());
        detectResult.setKeyword(list.get(0).getKeyword());
        detectRecorder.setFinishDate(new Date());
        detectResultService.save(detectResult);
        detectRecorderService.updateById(detectRecorder);
        return detectResult;
    }

    private synchronized DetectResult predict_decision_tree(Text t, String username) throws InterruptedException {
        DetectRecorder detectRecorder = new DetectRecorder();
        detectRecorder.setUsername(username);
        detectRecorder.setId(UUID.randomUUID().toString());
        detectRecorder.setTextId(t.getId());
        detectRecorder.setModel("decision_tree");
        detectRecorder.setStartDate(new Date());
        detectRecorderService.save(detectRecorder);
        // svm
        map.put("model", "decision_tree");
        messageSender.sendMessage("text", JSON.toJSONString(map));
        wait(120000);
        DetectResult detectResult = new DetectResult();
        detectResult.setDetectRecorderId(detectRecorder.getId());
        detectResult.setResult(list.get(0).getResult());
        detectResult.setKeyword(list.get(0).getKeyword());
        detectRecorder.setFinishDate(new Date());
        detectResultService.save(detectResult);
        detectRecorderService.updateById(detectRecorder);
        return detectResult;
    }

    private synchronized DetectResult predict_random_forest(Text t, String username) throws InterruptedException {
        DetectRecorder detectRecorder = new DetectRecorder();
        detectRecorder.setUsername(username);
        detectRecorder.setId(UUID.randomUUID().toString());
        detectRecorder.setTextId(t.getId());
        detectRecorder.setModel("random_forest");
        detectRecorder.setStartDate(new Date());
        detectRecorderService.save(detectRecorder);
        // svm
        map.put("model", "random_forest");
        messageSender.sendMessage("text", JSON.toJSONString(map));
        wait(120000);
        DetectResult detectResult = new DetectResult();
        detectResult.setDetectRecorderId(detectRecorder.getId());
        detectResult.setResult(list.get(0).getResult());
        detectResult.setKeyword(list.get(0).getKeyword());
        detectRecorder.setFinishDate(new Date());
        detectResultService.save(detectResult);
        detectRecorderService.updateById(detectRecorder);
        return detectResult;
    }

    private synchronized DetectResult predict_naive_bayes(Text t, String username) throws InterruptedException {
        DetectRecorder detectRecorder = new DetectRecorder();
        detectRecorder.setUsername(username);
        detectRecorder.setId(UUID.randomUUID().toString());
        detectRecorder.setTextId(t.getId());
        detectRecorder.setModel("naive_bayes");
        detectRecorder.setStartDate(new Date());
        detectRecorderService.save(detectRecorder);
        // svm
        map.put("model", "naive_bayes");
        messageSender.sendMessage("text", JSON.toJSONString(map));
        wait(120000);
        DetectResult detectResult = new DetectResult();
        detectResult.setDetectRecorderId(detectRecorder.getId());
        detectResult.setResult(list.get(0).getResult());
        detectResult.setKeyword(list.get(0).getKeyword());
        detectRecorder.setFinishDate(new Date());
        detectResultService.save(detectResult);
        detectRecorderService.updateById(detectRecorder);
        return detectResult;
    }

    private Text saveTexts(String text) {
        Text t = new Text();
        t.setId(UUID.randomUUID().toString());
        t.setFileId(null);
        t.setUrl(text);
        textService.save(t);
        return t;
    }
}
