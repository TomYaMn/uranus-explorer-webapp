-- Create 'e_service' table
CREATE TABLE e_service (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    e_service_name VARCHAR(155) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE
);

-- Create 'e_service_item' table
CREATE TABLE e_service_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    e_service_id BIGINT NOT NULL,
    e_service_item VARCHAR(155) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (e_service_id) REFERENCES e_service(id) ON DELETE CASCADE
);

-- Create 'field_type' table
CREATE TABLE field_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    input_type_name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE
);

-- Create 'submission' table
CREATE TABLE submission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    e_service_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (e_service_id) REFERENCES e_service(id) ON DELETE CASCADE
);

-- Create 'form_field' table
CREATE TABLE form_field (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    field_name VARCHAR(255) NOT NULL, 
    field_type_id BIGINT NOT NULL, 
    is_required BOOLEAN NOT NULL, 
    e_service_id BIGINT NOT NULL,
    field_options JSON NULL,  -- Store dropdown/checkbox options as JSON
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE,
    UNIQUE (field_name, e_service_id),
    FOREIGN KEY (field_type_id) REFERENCES field_type(id) ON DELETE CASCADE,
    FOREIGN KEY (e_service_id) REFERENCES e_service(id) ON DELETE CASCADE
);

-- Create 'form_field_value' table (optimized)
CREATE TABLE form_field_value (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    submission_id BIGINT NOT NULL, 
    form_field_id BIGINT NOT NULL, 
    user_input_value JSON NOT NULL,  -- Store all values in JSON
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (submission_id) REFERENCES submission(id) ON DELETE CASCADE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id) ON DELETE CASCADE
);

-- Create 'form_field_document' table
CREATE TABLE form_field_document (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    submission_id BIGINT NOT NULL, 
    form_field_id BIGINT NOT NULL, 
    document_url VARCHAR(255) NOT NULL,  
    document_type ENUM('pdf', 'jpg', 'png', 'docx') NOT NULL,  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (submission_id) REFERENCES submission(id) ON DELETE CASCADE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id) ON DELETE CASCADE
);

-- Create 'form_field_tooltip' table
CREATE TABLE form_field_tooltip (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tooltip_text VARCHAR(255) NOT NULL,
    form_field_id BIGINT NOT NULL,  -- Reference to the form_field
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id) ON DELETE CASCADE
);

-- Indexes for better performance
CREATE INDEX idx_submission_user_id ON submission (user_id);
CREATE INDEX idx_form_field_value_submission_id ON form_field_value (submission_id);
CREATE INDEX idx_form_field_value_field_id ON form_field_value (form_field_id);
