package com.yfl.hello.utils;

import com.alibaba.fastjson.JSON;
import com.yfl.hello.dto.CmdResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public final class CmdToolkit {


    /**
     * 读取控制命令的输出结果
     *
     * @param cmd 命令
     * @return 控制命令的输出结果
     * @throws IOException
     */
    public CmdResponse readConsole(String... cmd) {
        int code = 1;
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();
        try {
            ProcessBuilder pBuilder = new ProcessBuilder(cmd);
            pBuilder.redirectErrorStream(true);
            Process process = pBuilder.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
                lines.add(line);
            }
            try {
                WatchThread wt = new WatchThread(process);
                wt.start();
                code = process.waitFor();
                ArrayList<String> commandStream = wt.getStream();
                wt.setOver(true);
                log.info("wait:stream:", JSON.toJSONString(commandStream));
            } catch (InterruptedException e) {
                log.error("cmd:wait:error:", e);
            }
        } catch (IOException e) {
            log.error("cmd:error:", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {

            }
            return new CmdResponse(code, lines);
        }
    }


    class WatchThread extends Thread {
        Process p;
        boolean over;
        ArrayList<String> stream;

        public WatchThread(Process p) {
            this.p = p;
            over = false;
            stream = new ArrayList<String>();
        }

        @Override
        public void run() {
            Scanner br = null;
            try {
                if (p == null) {
                    return;
                }
                br = new Scanner(p.getInputStream());
                while (true) {
                    if (p == null || over) {
                        break;
                    }
                    while (br.hasNextLine()) {
                        String tempStream = br.nextLine();
                        if (tempStream.trim() == null || tempStream.trim().equals("")) {
                            continue;
                        }
                        stream.add(tempStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                br.close();
            }
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public ArrayList<String> getStream() {
            return stream;
        }
    }
}