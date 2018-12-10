/*
 * Copyright 2018 Qunar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.com.qunar.pay.trade.api.card.service.usercard.UserCardQueryFacade
 */

package qunar.tc.qmq.base;

/**
 * 大字符串支持工具
 * 
 * Created by zhaohui.yu 5/4/18
 */
class LargeStringUtil {
    private static final int _32K = (32 * 1024) / 4;

    /**
     * 数据的长度不大于32K,直接设置整个数据. 如果大于32K,就分段设置key#part0,32K数据段.
     * 
     * @param msg
     *            消息
     * @param key
     *            消息属性key
     * @param data
     *            消息数据
     */
    static void setLargeString(BaseMessage msg, String key, String data) {
        int len = data.length();
        if (len <= _32K) {
            msg.setProperty(key, data);
            return;
        }

        // 消息设置为大数据消息
        msg.isBigMessage = true;
        // 分段ID
        int partIdx = 0;
        // 数据分段
        for (int remain = len; remain > 0; remain -= _32K) {
            final int beginIdx = partIdx * _32K;
            final int endIdx = beginIdx + Math.min(_32K, remain);
            final String part = data.substring(beginIdx, endIdx);
            msg.setProperty(buildPartKey(key, partIdx), part);
            partIdx += 1;
        }
    }

    /**
     * 从消息中获取大数据
     * 
     * @param msg
     *            消息
     * @param key
     *            消息属性key
     * @return
     */
    static String getLargeString(BaseMessage msg, String key) {
        String small = msg.getStringProperty(key);
        if (small != null)
            return small;

        // 如果按key获取数据, 获取不到, 继续按key#part方式查找,合并分段数据返回
        StringBuilder result = new StringBuilder();
        int partIdx = 0;
        while (true) {
            String part = msg.getStringProperty(buildPartKey(key, partIdx));
            if (part == null) {
                break;
            }
            partIdx += 1;
            result.append(part);
        }
        return result.toString();
    }

    /**
     * 获取原key的分段partkey
     * 
     * @param key
     * @param idx
     * @return
     */
    private static String buildPartKey(String key, int idx) {
        return key + "#part" + idx;
    }
}
