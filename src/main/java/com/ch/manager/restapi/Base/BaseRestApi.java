package com.ch.manager.restapi.Base;

import com.ch.manager.api.WxMsgLogApi;
import com.ch.manager.constants.Constants;
import com.ch.manager.entity.MtWxMsgLog;
import com.ch.manager.enums.EnumWxMsgType;
import com.ch.manager.utils.SHA1Util;
import com.ch.manager.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class BaseRestApi {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

}
