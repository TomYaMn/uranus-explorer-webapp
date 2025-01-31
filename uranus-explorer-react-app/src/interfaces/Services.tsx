export interface ServiceItem {
    item: string;
}

export interface FormField {
    fieldName: string;
    fieldTypeId: number;
    eServiceId: number;
    tooltip?: string;
    fieldOptions?: string | null;  // Changed from 'options' to 'fieldOptions' to align with the data
    required: boolean;
    active: boolean;
}

export interface Service {
    serviceId: number;
    eServiceName: string;  // This represents the name of the service
    formFields: FormField[];
    serviceItems: ServiceItem[];
}