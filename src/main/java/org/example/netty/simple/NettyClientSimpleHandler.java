package org.example.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ProjectName: netty-study
 * @Package: org.example.netty.simple
 * @ClassName: NettyClientSimepleHandler
 * @Author: lee.min
 * @Description:
 * @Date: 2020/12/13 5:26 下午
 */
public class NettyClientSimpleHandler extends ChannelInboundHandlerAdapter {
    //当通道就绪会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client"+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client"+ctx);
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("server back msg "+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("ip"+ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
