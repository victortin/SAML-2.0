/*
 * Copyright [2007] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.xml.security.x509;

import javax.security.auth.x500.X500Principal;

import org.opensaml.xml.security.Criteria;

/**
 * An implementation of {@link Criteria} which specifies criteria based on
 * X.509 certificate subject name.
 */
public final class X509SubjectNameCriteria implements Criteria {
    
    /** X.509 certificate subject name. */
    private X500Principal subjectName;
    
    /**
     * Constructor.
     *
     * @param subject certificate subject name
     */
    public X509SubjectNameCriteria(X500Principal subject) {
        setSubjectName(subject);
    }

    /**
     * Get the subject name.
     * 
     * @return Returns the subject name
     */
    public X500Principal getSubjectName() {
        return subjectName;
    }

    /**
     * Set the serial number.
     * 
     * @param subject The subject name
     */
    public void setSubjectName(X500Principal subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject principal criteria value must be supplied");
        }
        this.subjectName = subject;
    }

}
