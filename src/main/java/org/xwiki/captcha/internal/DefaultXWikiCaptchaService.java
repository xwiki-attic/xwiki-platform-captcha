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

import java.util.List;
import java.util.ArrayList;

import org.xwiki.captcha.CaptchaVerifier;
import org.xwiki.captcha.CaptchaVerifierNotFoundException;
import org.xwiki.captcha.XWikiCaptchaService;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.Requirement;
import org.xwiki.component.logging.AbstractLogEnabled;

/**
 * Provides access to the classes implementing Captcha.
 *
 * @version $Id$
 * @since 2.2M2
 */
@Component
public class DefaultXWikiCaptchaService extends AbstractLogEnabled implements XWikiCaptchaService
{
    /** A Map of all captchas by their names. */
    @Requirement
    private ComponentManager componentManager;

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.captcha.XWikiCaptchaService#getCaptchaVerifier(java.lang.String)
     */
    public CaptchaVerifier getCaptchaVerifier(String captchaName) throws CaptchaVerifierNotFoundException
    {
        CaptchaVerifier captchaVerifier;
        try {
            captchaVerifier = componentManager.lookup(CaptchaVerifier.class, captchaName);
        } catch (ComponentLookupException e) {
            throw new CaptchaVerifierNotFoundException("The CaptchaVerifier " + captchaName
                                                       + " could not be found, try listCaptchaNames()"
                                                       + " for a list of registered CaptchaVerifiers.");
        }
        return captchaVerifier;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.captcha.XWikiCaptchaService#listCaptchaNames()
     */
    public List<String> listCaptchaNames()
    {
        List<String> captchaNames = new ArrayList<String>();
        try {
            captchaNames.addAll(componentManager.lookupMap(CaptchaVerifier.class).keySet());
        } catch (ComponentLookupException e) {
            getLogger().error("Couldn't get list of CaptchaVerifier names " + e.getMessage());
        }
        return captchaNames;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.xwiki.captcha.XWikiCaptchaService#isEnabled()
     */
    @Deprecated
    public boolean isEnabled()
    {
        return true;
    }
}
