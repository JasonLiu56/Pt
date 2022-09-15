package com.jxcia.pt.controller;

import cn.hutool.core.codec.Base64Encoder;
import com.google.code.kaptcha.Producer;
import com.jxcia.pt.common.Constant;
import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.vo.CaptchaVo;
import com.jxcia.pt.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@RestController
@Api(tags = "验证码模块", value = "验证码模块", description="验证码模块接口")
public class KaptchaController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取验证码")
    @GetMapping("/captcha")
    public Result Captcha() throws Exception {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        Base64Encoder encoder = new Base64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 存储到redis,有效期2分钟
        redisUtil.hset(Constant.CAPTCHA_KEY, key, code, 120L);
        CaptchaVo captchaVo = new CaptchaVo(key, base64Img);

        return Result.succ(captchaVo);
    }

}
