package uk.gov.service.notify;

import java.util.Map;

public interface NotificationClientApi {

    /**
     * The sendEmail method will create an HTTPS POST request. A JWT token will be created and added as an Authorization header to the request.
     *
     * @param templateId      The template id is visible on the template page in the application.
     * @param emailAddress    The email address
     * @param personalisation Map representing the placeholders for the template if any. For example, key=name value=Bob
     *                        Can be an empty map or null when the template does not require placeholders.
     * @param reference       A reference specified by the service for the notification. Get all notifications can be filtered by this reference.
     *                        This reference can be unique or used used to refer to a batch of notifications.
     *                        Can be an empty string or null, when you do not require a reference for the notifications.
     * @return <code>SendEmailResponse</code>
     * @throws NotificationClientException
     */
    SendEmailResponse sendEmail(String templateId, String emailAddress, Map<String, String> personalisation, String reference) throws NotificationClientException;

    /**
     * The sendSms method will create an HTTPS POST request. A JWT token will be created and added as an Authorization header to the request.
     *
     * @param templateId      The template id is visible from the template page in the application.
     * @param phoneNumber              The mobile phone number
     * @param personalisation Map representing the placeholders for the template if any. For example, key=name value=Bob
     *                        Can be an empty map or null when the template does not require placeholders.
     * @param reference       A reference specified by the service for the notification. Get all notifications can be filtered by this reference.
     *                        This reference can be unique or used used to refer to a batch of notifications.
     *                        Can be an empty string or null, when you do not require a reference for the notifications.
     * @return <code>SendSmsResponse</code>
     * @throws NotificationClientException
     */
    SendSmsResponse sendSms(String templateId, String phoneNumber, Map<String, String> personalisation, String reference) throws NotificationClientException;

    /**
     * The getNotificationById method will return a <code>Notification</code> for a given notification id.
     * The id is can be retrieved from the <code>NotificationResponse</code> of a <code>sendEmail</code> or <code>sendSms</code> request.
     *
     * @param notificationId The id of the notification.
     * @return <code>Notification</code>
     * @throws NotificationClientException
     */
    Notification getNotificationById(String notificationId) throws NotificationClientException;

    /**
     * The getNotifications method will create a GET HTTPS request to retrieve all the notifications.
     *
     * @param status If status is not empty or null notifications will only return notifications for the given status.
     *               Possible statuses are created|sending|delivered|permanent-failure|temporary-failure|technical-failure
     * @param notification_type If notification_type is not empty or null only notifications of the given status will be returned.
     *                          Possible notificationTypes are sms|email
     * @param reference If reference is not empty or null only the notifications with that reference are returned.
     * @param olderThanId If olderThanId is not empty or null only the notifications older than that notification id are returned.
     * @return <code>NotificationList</code>
     * @throws NotificationClientException
     */
    NotificationList getNotifications(String status, String notification_type, String reference, String olderThanId) throws NotificationClientException;

    /**
     * The getTemplateById returns a <code>Template</code> given the template id.
     *
     * @param templateId The template id is visible on the template page in the application.
     * @return <code>Template</code>
     * @throws NotificationClientException
     */
    Template getTemplateById(String templateId) throws NotificationClientException;

    /**
     * The getTemplateVersion returns a <code>Template</code> given the template id and version.
     *
     * @param templateId The template id is visible on the template page in the application.
     * @param version The version of the template to return
     * @return <code>Template</code>
     * @throws NotificationClientException
     */
    Template getTemplateVersion(String templateId, int version) throws NotificationClientException;

    /**
     * Returns all the templates for your service. Filtered by template type if not null.
     *
     * @param templateType If templateType is not empty or null templates will be filtered by type.
     *          Possible template types are email|sms|letter
     * @return <code>TemplateList</code>
     * @throws NotificationClientException
     */
    TemplateList getAllTemplates(String templateType) throws NotificationClientException;

    /**
     * The getTemplatePreview returns a template with the placeholders replaced with the given personalisation.
     *
     * @param templateId The template id is visible from the template page in the application.
     * @param personalisation Map representing the placeholders for the template if any. For example, key=name value=Bob
     *                        Can be an empty map or null when the template does not require placeholders.
     * @return <code>Template</code>
     * @throws NotificationClientException
     */
    TemplatePreview getTemplatePreview(String templateId, Map<String, String> personalisation) throws NotificationClientException;
}
