/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */
package org.forgerock.openam.audit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory for creation of AuditEvent builders.
 *
 * Facilitates mocking of event builders.
 *
 * @since 13.0.0
 */
@Singleton
public class AuditEventFactory {

    private final AuditServiceProvider auditServiceProvider;

    /**
     * Constructs a new {@code AuditEventFactory}.
     *
     * @param auditServiceProvider A {@code AuditServiceProvider} instance.
     */
    @Inject
    public AuditEventFactory(AuditServiceProvider auditServiceProvider) {
        this.auditServiceProvider = auditServiceProvider;
    }

    /**
     * Creates a new AMAccessAuditEventBuilder.
     *
     * @return AMAccessAuditEventBuilder
     */
    public AMAccessAuditEventBuilder accessEvent() {
        if (auditServiceProvider.getDefaultAuditService().isResolveHostNameEnabled()) {
            return new AMAccessAuditEventBuilder().withReverseDnsLookup();
        } else {
            return new AMAccessAuditEventBuilder();
        }
    }

    /**
     * Creates a new AMAccessAuditEventBuilder.
     *
     * @param realm The realm in which the audit event occurred.
     * @return AMAccessAuditEventBuilder
     */
    public AMAccessAuditEventBuilder accessEvent(String realm) {
        if (auditServiceProvider.getAuditService(realm).isResolveHostNameEnabled()) {
            return new AMAccessAuditEventBuilder().withReverseDnsLookup();
        } else {
            return new AMAccessAuditEventBuilder();
        }
    }

    /**
     * Creates a new AMActivityAuditEventBuilder.
     *
     * @return AMActivityAuditEventBuilder
     */
    public AMActivityAuditEventBuilder activityEvent() {
        return new AMActivityAuditEventBuilder();
    }

}