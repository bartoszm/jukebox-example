/*
 * Copyright © 2017 Amartus and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.amartus.examples.impl.hello;

import org.opendaylight.yang.gen.v1.urn.com.amartus.example.hello.rev171108.HelloService;
import org.opendaylight.yang.gen.v1.urn.com.amartus.example.hello.rev171108.HelloWorldInput;
import org.opendaylight.yang.gen.v1.urn.com.amartus.example.hello.rev171108.HelloWorldOutput;
import org.opendaylight.yang.gen.v1.urn.com.amartus.example.hello.rev171108.HelloWorldOutputBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;

import java.util.concurrent.Future;

/**
 * @author bartosz.michalik@amartus.com
 */
public class HeloServiceImpl implements HelloService {
    @Override
    public Future<RpcResult<HelloWorldOutput>> helloWorld(HelloWorldInput input) {
        HelloWorldOutputBuilder builder = new HelloWorldOutputBuilder();
        HelloWorldOutput output = builder.setGreeting("Hello " + input.getName()).build();
        return RpcResultBuilder.success(output).buildFuture();

    }
}
