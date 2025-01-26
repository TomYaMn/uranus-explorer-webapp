-- Create 'e_service' table
CREATE TABLE e_service (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    category VARCHAR(155) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE
);

-- Create 'e_service_item' table
CREATE TABLE e_service_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    e_service_id BIGINT NOT NULL,
    item VARCHAR(155) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_service_items_to_services FOREIGN KEY (e_service_id) REFERENCES e_service(id)
);

-- Drop field_type table if it exists
DROP TABLE IF EXISTS field_type;

-- Drop form_field table if it exists
DROP TABLE IF EXISTS form_field;

-- Create field_type table
CREATE TABLE field_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    field_type_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
);

-- Create form_field table
CREATE TABLE form_field (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    field_name VARCHAR(255) NOT NULL, 
    field_type_id BIGINT NOT NULL, 
    is_required BOOLEAN NOT NULL, 
    e_service_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (field_type_id) REFERENCES field_type(id),
    FOREIGN KEY (e_service_id) REFERENCES e_service(id)
);

-- Create form_field_option table
CREATE TABLE form_field_option (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    form_field_id BIGINT NOT NULL, 
    option_value VARCHAR(255) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id)
);

-- Create form_field_tooltips table
CREATE TABLE form_field_tooltips (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    form_field_id BIGINT NOT NULL, 
    tooltip_text TEXT NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id)
);

-- Create form_field_value table
CREATE TABLE form_field_value (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    form_field_id BIGINT NOT NULL, 
    user_input_value TEXT NOT NULL, 
    user_id BIGINT NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id)
);

-- Create form_field_document table with base64url encoding for document and document_type column
CREATE TABLE form_field_document (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    form_field_id BIGINT NOT NULL, 
    document_url VARCHAR(255) NOT NULL,  -- Store the URL or path to the document in MinIO
    document_type VARCHAR(50) NOT NULL,  -- Store the document type (e.g., 'pdf', 'jpg', 'docx')
    user_id BIGINT NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    status BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (form_field_id) REFERENCES form_field(id)
);
