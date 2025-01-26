-- Populate Services Table
INSERT INTO e_service (id, e_service_name, created_at, updated_at, status) VALUES
(1, 'Registration and Booking', NOW(), NOW(), TRUE),
(2, 'Information and Education', NOW(), NOW(), TRUE),
(3, 'Pre-Departure Preparations', NOW(), NOW(), TRUE),
(4, 'Communication and Community', NOW(), NOW(), TRUE),
(5, 'Personalization Options', NOW(), NOW(), TRUE),
(6, 'On-Mission Features', NOW(), NOW(), TRUE),
(7, 'Post-Mission Services', NOW(), NOW(), TRUE);

-- Populate Service Items Table
INSERT INTO e_service_item (e_service_id, e_service_item, created_at, updated_at, status) VALUES
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
INSERT INTO field_type (field_type_name, created_at, updated_at, status) VALUES
('text', NOW(), NOW(), TRUE),
('email', NOW(), NOW(), TRUE),
('date', NOW(), NOW(), TRUE),
('dropdown', NOW(), NOW(), TRUE),
('checkbox', NOW(), NOW(), TRUE),
('textarea', NOW(), NOW(), TRUE),
('file', NOW(), NOW(), TRUE);

-- Populate form_field table
-- Service 1: Registration and Booking
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, created_at, updated_at, status) VALUES
('Full Name', 1, TRUE, 1, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 1, NOW(), NOW(), TRUE),
('Date of Birth', 3, TRUE, 1, NOW(), NOW(), TRUE),
('Preferred Exploration Package', 4, TRUE, 1, NOW(), NOW(), TRUE),
('Supporting Documents', 7, FALSE, 1, NOW(), NOW(), TRUE);

-- Service 2: Information and Education
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, created_at, updated_at, status) VALUES
('Full Name', 1, TRUE, 2, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 2, NOW(), NOW(), TRUE),
('Webinar Topic of Interest', 4, TRUE, 2, NOW(), NOW(), TRUE),
('Preferred Webinar Date', 3, TRUE, 2, NOW(), NOW(), TRUE),
('Post-Webinar Feedback', 6, FALSE, 2, NOW(), NOW(), TRUE);

-- Service 3: Pre-Departure Preparations
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, created_at, updated_at, status) VALUES
('Full Name', 1, TRUE, 3, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 3, NOW(), NOW(), TRUE),
('Date of Submission', 3, TRUE, 3, NOW(), NOW(), TRUE),
('Medical Records', 7, TRUE, 3, NOW(), NOW(), TRUE),
('Additional Notes', 6, FALSE, 3, NOW(), NOW(), TRUE);

-- Service 4: Communication and Community
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, created_at, updated_at, status) VALUES
('Full Name', 1, TRUE, 4, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 4, NOW(), NOW(), TRUE),
('Event Preferences', 5, FALSE, 4, NOW(), NOW(), TRUE),
('Preferred Notification Method', 4, TRUE, 4, NOW(), NOW(), TRUE);

-- Service 5: Personalization Options
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, created_at, updated_at, status) VALUES
('Full Name', 1, TRUE, 5, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 5, NOW(), NOW(), TRUE),
('Exploration Theme', 4, TRUE, 5, NOW(), NOW(), TRUE),
('Custom Add-Ons', 5, FALSE, 5, NOW(), NOW(), TRUE),
('Special Requests', 6, FALSE, 5, NOW(), NOW(), TRUE);

-- Service 6: On-Mission Features
INSERT INTO form_field (field_name, field_type_id, is_required, e_service_id, created_at, updated_at, status) VALUES
('Full Name', 1, TRUE, 6, NOW(), NOW(), TRUE),
('Email Address', 2, TRUE, 6, NOW(), NOW(), TRUE),
('Tracking Start Date', 3, TRUE, 6, NOW(), NOW(), TRUE),
('Preferred Updates', 4, TRUE, 6, NOW(), NOW(), TRUE),
('Family Access Request', 5, FALSE, 6, NOW(), NOW(), TRUE);


