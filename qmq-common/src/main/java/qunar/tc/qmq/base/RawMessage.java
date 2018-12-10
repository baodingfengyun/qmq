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

import io.netty.buffer.ByteBuf;

/**
 * 原始消息
 * 
 * @author yiqun.fan create on 17-7-4.
 */
public class RawMessage {
    /** 消息头 */
    private final MessageHeader header;
    /** 数据缓冲 */
    private final ByteBuf body;
    /** 数据体大小 */
    private final int bodySize;

    public RawMessage(MessageHeader header, ByteBuf body, int size) {
        this.header = header;
        this.body = body;
        this.bodySize = size;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public ByteBuf getBody() {
        return body;
    }

    public int getBodySize() {
        return bodySize;
    }

    public void setSubject(String subject) {
        header.setSubject(subject);
    }

    public boolean isHigh() {
        return (header.getFlag() & 1) == 0;
    }
}
