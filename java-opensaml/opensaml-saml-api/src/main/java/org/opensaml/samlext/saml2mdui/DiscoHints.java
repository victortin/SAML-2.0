/*
 * Copyright 2010 University Corporation for Advanced Internet Development, Inc.
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

package org.opensaml.samlext.saml2mdui;

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.common.SAMLObject;
import org.opensaml.xml.XMLObject;

/**
 * See IdP Discovery and Login UI Metadata Extension Profile.
 * 
 * @author RDW 27/Aug/2010
 * 
 * Reflects the DiscoHints 
 */
public interface DiscoHints extends SAMLObject {
    /** Namespace for Discovery Service metadata extensions. */
    public static final String MDUI_NS = "urn:oasis:names:tc:SAML:metadata:ui";

    /** Default namespace prefix used by this library. */
    public static final String  MDUI_PREFIX = "mdui";

    /** Name of the element inside the Extensions. */
    public static final String DEFAULT_ELEMENT_LOCAL_NAME = "DiscoHints";

    /** Default element name. */
    public static final QName DEFAULT_ELEMENT_NAME = new QName(MDUI_NS, DEFAULT_ELEMENT_LOCAL_NAME,
            MDUI_PREFIX);
    
    /** 
     * The <IPHint> element specifies a set of [CIDR] blocks associated with, 
     *  or serviced by, the entity.  Both IPv4 and IPv6 CIDR blocks MUST be supported.
     * 
     * @return hints
     */
    public List <IPHint> getIPHints();
    
    /** The <DomainHint> element specifies a set of DNS domains associated with, 
     * or serviced by, the entity.
     * @return hints.
     */
    public List <DomainHint> getDomainHints();
    
    /** The <GeolocationHint> element specifies the geographic coordinates associated 
     *  with, or serviced by, the entity.  Coordinates are given in decimal form using
     *  the World Geodetic System (2d) coordinate system.
     * 
     * @return hints
     */
    public List <GeolocationHint> getGeolocationHints();
    
    /**
     * Get the list of all children of this element.
     * 
     * @return the list of all XMLObject children
     */
    public List <XMLObject> getXMLObjects(); 
    
    /**
     * Get the list of all children of this element which have the specified name or type.
     * 
     * @param typeOrName the element name or type of the desired list of elements
     * 
     * @return the list of all XMLObject children
     */
    public List <XMLObject> getXMLObjects(QName typeOrName); 
    
}
