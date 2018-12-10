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

package qunar.tc.qmq;

/**
 * 消息生产接口
 * 
 * Created by zhaohui.yu 10/31/16
 */
public interface ProduceMessage {

    /** 获取消息ID */
    String getMessageId();

    /** 获取分类 */
    String getSubject();

    /** 发送 */
    void send();

    /** 异常 */
    void error(Exception e);

    /** 失败 */
    void failed();

    /** 阻塞 */
    void block();

    /** 完成 */
    void finish();

    /** 获取消息 */
    Message getBase();

    /** 开始发送追踪 */
    void startSendTrace();
}
