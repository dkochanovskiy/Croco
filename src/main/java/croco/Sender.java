package croco;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

class Sender {

    /**
    *   Logger init
     */
    private static final Logger logger = Logger.getLogger(Sender.class);

    void sender(String employeeId, String taskId, String start, String end,  String comment){

        try(
                CloseableHttpClient httpClient = HttpClients.createDefault()
        ) {
            HttpPost request = new HttpPost("https://saas.crocotime.net/70ac75b74e0d4805888824636ac4575e/");
            StringEntity params =new StringEntity(
                    "{\n" +
                            "    \"server_token\": \"63714f7317810d0cd3d34021760e5982530c3e0b22228ffa8b926adf50db7d36\",\n" +
                            "    \"app_version\": \"5.3.0\",\n" +
                            "    \"controller\": \"WorkspaceActionController\",\n" +
                            "    \"action\": \"insert\",\n" +
                            "    \"query\": {\n" +
                            "        \"domain\": \"task_timings\",\n" +
                            "        \"record\": {\n" +
                            "            \"task_id\": " + taskId + ",\n" +
                            "            \"employee_id\": " + employeeId + ",\n" +
                            "            \"project_id\": 1,\n" +
                            "            \"priority\": 100,\n" +
                            "            \"begin\": " + start + ",\n" +
                            "            \"end\": " + end + ",\n" +
                            "            \"comment\": \"" + comment + "\"\n" +
                            "        }\n" +
                            "    }\n" +
                            "}");
            request.addHeader("content-type", "application/json; charset=utf-8");
            request.addHeader("Accept","application/json");
            request.setEntity(params);

            try (CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity));
            }
        }catch (IOException ex) {
            logger.trace("IOException! " + ex);
        }
    }
}
