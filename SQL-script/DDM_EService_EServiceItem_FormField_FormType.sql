-- Populate Services Table
INSERT INTO e_service (id, e_service_name, created_at, updated_at, is_active) VALUES
(1, 'Registration and Booking', NOW(), NOW(), TRUE),
(2, 'Information and Education', NOW(), NOW(), TRUE),
(3, 'Pre-Departure Preparations', NOW(), NOW(), TRUE),
(4, 'Communication and Community', NOW(), NOW(), TRUE),
(5, 'Personalization Options', NOW(), NOW(), TRUE),
(6, 'On-Mission Features', NOW(), NOW(), TRUE),
(7, 'Post-Mission Services', NOW(), NOW(), TRUE);

-- Populate Service Items Table
INSERT INTO e_service_item (e_service_id, e_service_item, created_at, updated_at, is_active) VALUES
(1, 'Sign-Up for Exploration: Register for the Uranus exploration program.', NOW(), NOW(), TRUE),
(1, 'Seat Selection: Choose your preferred seat on the spacecraft.', NOW(), NOW(), TRUE),
(1, 'Package Customization: Tailor your exploration package based on your interests.', NOW(), NOW(), TRUE),
(1, 'Secure Payment Portal: Make payments securely for registrations, upgrades, and extras.', NOW(), NOW(), TRUE),

(2, 'Mission Overview: Access detailed information about the exploration mission.', NOW(), NOW(), TRUE),
(2, 'Virtual Uranus Tour: Explore Uranus and its moons in 3D or VR.', NOW(), NOW(), TRUE),
(2, 'Astronomy Learning Hub: Learn about Uranus and space exploration through videos, articles, and webinars.', NOW(), NOW(), TRUE),

(3, 'Health Assessment Submission: Upload medical records and receive clearance for space travel.', NOW(), NOW(), TRUE),
(3, 'Training Modules: Access e-learning resources to prepare for space travel.', NOW(), NOW(), TRUE),
(3, 'Gear and Equipment Store: Purchase exploration essentials, such as space suits and gadgets.', NOW(), NOW(), TRUE),

(4, 'Live Chat Support: Get real-time assistance for your queries.', NOW(), NOW(), TRUE),
(4, 'Explorer Forums: Connect with fellow participants and share experiences.', NOW(), NOW(), TRUE),
(4, 'Event Notifications: Stay updated with mission schedules and announcements.', NOW(), NOW(), TRUE),

(5, 'Mission Customization: Select your preferred exploration theme (e.g., research, adventure, luxury).', NOW(), NOW(), TRUE),
(5, 'Travel Journals: Document your journey with photos and stories.', NOW(), NOW(), TRUE),
(5, 'Custom Souvenirs: Order personalized merchandise, such as patches or certificates.', NOW(), NOW(), TRUE),

(6, 'Live Mission Tracking: Monitor spacecraft progress and conditions in real time.', NOW(), NOW(), TRUE),
(6, 'Daily Mission Updates: Receive news and milestones about the journey.', NOW(), NOW(), TRUE),
(6, 'Family Live Streaming: Share launch and mission updates with loved ones.', NOW(), NOW(), TRUE),

(7, 'Completion Certificates: Download your official mission participation certificate.', NOW(), NOW(), TRUE),
(7, 'Feedback Portal: Provide feedback to help improve future missions.', NOW(), NOW(), TRUE),
(7, 'Alumni Network Access: Join the exclusive community of Uranus explorers for discounts and special events.', NOW(), NOW(), TRUE);

-- Populate field_type table
INSERT INTO field_type (id, input_type_name, created_at, updated_at, is_active) VALUES
(1, 'text', NOW(), NOW(), TRUE),
(2, 'email', NOW(), NOW(), TRUE),
(3, 'password', NOW(), NOW(), TRUE),
(4, 'number', NOW(), NOW(), TRUE),
(5, 'date', NOW(), NOW(), TRUE),
(6, 'datetime-local', NOW(), NOW(), TRUE),
(7, 'time', NOW(), NOW(), TRUE),
(8, 'month', NOW(), NOW(), TRUE),
(9, 'week', NOW(), NOW(), TRUE),
(10, 'radio', NOW(), NOW(), TRUE),
(11, 'dropdown', NOW(), NOW(), TRUE),
(12, 'checkbox', NOW(), NOW(), TRUE),
(13, 'textarea', NOW(), NOW(), TRUE),
(14, 'file', NOW(), NOW(), TRUE),
(15, 'hidden', NOW(), NOW(), TRUE),
(16, 'range', NOW(), NOW(), TRUE),
(17, 'tel', NOW(), NOW(), TRUE),
(18, 'url', NOW(), NOW(), TRUE),
(19, 'color', NOW(), NOW(), TRUE),
(20, 'search', NOW(), NOW(), TRUE);




-- Populat both tooltip and form field same time
START TRANSACTION;

-- Service 1: Registration and Booking
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, field_options, created_at, updated_at, is_active) VALUES
('Full Name', 1, TRUE, 1, NULL, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 1, NULL, NOW(), NOW(), TRUE),
('Date of Birth', 5, TRUE, 1, NULL, NOW(), NOW(), TRUE),
('Preferred Exploration Package', 11, TRUE, 1, NULL, NOW(), NOW(), TRUE),
('Supporting Documents', 14, FALSE, 1, NULL, NOW(), NOW(), TRUE);

-- Get last inserted ids for form_field
SET @last_inserted_id = LAST_INSERT_ID();

-- Insert into form_field_tooltip for Service 1
INSERT INTO form_field_tooltip (tooltip_text, form_field_id, created_at, updated_at, is_active) VALUES
('Enter your full legal name as per official documents.', @last_inserted_id, NOW(), NOW(), TRUE),
('Enter a valid email address to receive updates and communications.', @last_inserted_id + 1, NOW(), NOW(), TRUE),
('Enter your date of birth for verification and age-related considerations.', @last_inserted_id + 2, NOW(), NOW(), TRUE),
('Select your preferred exploration package based on your interests and budget.', @last_inserted_id + 3, NOW(), NOW(), TRUE),
('Upload any relevant supporting documents such as identification or medical forms.', @last_inserted_id + 4, NOW(), NOW(), TRUE);

-- Service 2: Information and Education
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, field_options, created_at, updated_at, is_active) VALUES
('Full Name', 1, TRUE, 2, NULL, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 2, NULL, NOW(), NOW(), TRUE),
('Webinar Topic of Interest', 11, TRUE, 2, NULL, NOW(), NOW(), TRUE),
('Preferred Webinar Date', 5, TRUE, 2, NULL, NOW(), NOW(), TRUE),
('Post-Webinar Feedback', 13, FALSE, 2, NULL, NOW(), NOW(), TRUE);

-- Get last inserted ids for form_field
SET @last_inserted_id = LAST_INSERT_ID();

-- Insert into form_field_tooltip for Service 2
INSERT INTO form_field_tooltip (tooltip_text, form_field_id, created_at, updated_at, is_active) VALUES
('Enter your full legal name as per official documents.', @last_inserted_id, NOW(), NOW(), TRUE),
('Enter a valid email address to receive updates and communications.', @last_inserted_id + 1, NOW(), NOW(), TRUE),
('Select the topic of the webinar that interests you the most.', @last_inserted_id + 2, NOW(), NOW(), TRUE),
('Select the preferred date for your webinar.', @last_inserted_id + 3, NOW(), NOW(), TRUE),
('Provide feedback after the webinar is completed.', @last_inserted_id + 4, NOW(), NOW(), TRUE);

-- Continue the same pattern for Services 3 to 6

COMMIT;
