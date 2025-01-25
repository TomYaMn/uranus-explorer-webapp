export interface ServiceItem {
    item: string;
}

export interface FormField {
    fieldName: string;
    fieldType: string;
    required: boolean;
}

export interface Service {
    category: string;
    formFields: FormField[];
    serviceItems: ServiceItem[];
}
