/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.captcha.internal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.module.web.image.ImageToJpegHelper;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * Extensions of AbstractImageCaptchaAction create jpeg image captchas.
 * 
 * @version $Id$
 * @since 2.2M2
 */
public abstract class AbstractImageCaptchaAction extends AbstractCaptchaAction<ImageCaptchaService>
{
    /**
     * {@inheritDoc}
     *
     * @see AbstractCaptchaAction#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     */
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
                                 HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ImageToJpegHelper.flushNewCaptchaToResponse(request,
                                                    response,
                                                    null,
                                                    getCaptchaService(),
                                                    getUserId(request),
                                                    request.getLocale());
        return null;
    }
}
