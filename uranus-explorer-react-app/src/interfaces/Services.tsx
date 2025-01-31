export interface ServiceItem {
    item: string;
}

export interface FormField {
    fieldName: string;
    fieldTypeName: string;
    options?: string[];  // Change 'option' to 'options' and define it as an array of strings
    tooltip?: string;
    required: boolean;
}

export interface Service {
    category: string;
    formFields: FormField[];
    serviceItems: ServiceItem[];
}