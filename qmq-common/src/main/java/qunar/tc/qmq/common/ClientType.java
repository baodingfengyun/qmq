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

package qunar.tc.qmq.common;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端类型: 生产者, 消费者, 其它, 延迟生产者
 * 
 * @author yiqun.fan create on 17-8-22.
 */
public enum ClientType {
    PRODUCER(1),
    CONSUMER(2),
    OTHER(3),
    DELAY_PRODUCER(4);

    // 此map用于后面的of方法, 如果是使用频繁的类型, 这样做性能更好
    private static final ImmutableMap<Integer, ClientType> INSTANCES;

    static {
        final Map<Integer, ClientType> result = new HashMap<>();
        for (final ClientType type : values()) {
            result.put(type.getCode(), type);
        }
        INSTANCES = ImmutableMap.copyOf(result);
    }

    private int code;

    ClientType(int code) {
        this.code = code;
    }

    public static ClientType of(final int code) {
        ClientType type = INSTANCES.get(code);
        return type == null ? OTHER : type;
    }

    public int getCode() {
        return code;
    }

    public boolean isProducer() {
        return code == PRODUCER.code || code == DELAY_PRODUCER.code;
    }

    public boolean isConsumer() {
        return code == CONSUMER.code;
    }
}
