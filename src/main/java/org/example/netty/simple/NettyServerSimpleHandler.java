package org.example.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ProjectName: netty-study
 * @Package: org.example.netty.simple
 * @ClassName: NettyServerSimpleHandler
 * @Author: lee.min
 * @Description:
 * @Date: 2020/12/13 5:07 下午
 */
public class NettyServerSimpleHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取客户端时间
     * @param ctx 上下文对象，包含了channel，pipeline,ip
     * @param msg 客户端的数据,默认是Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("当前线程 "+Thread.currentThread().getName());
        Channel channel = ctx.channel();
        System.out.println("channel"+channel.getClass());
        System.out.println("server ctx:"+ctx);
        ByteBuf byteBuf = (ByteBuf)msg;
        Thread.sleep(1000*10);
        System.out.println("receive msg "+byteBuf.toString(CharsetUtil.UTF_8)+" ip"+ctx.channel().remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
