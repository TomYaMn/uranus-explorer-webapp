import React from 'react';
import { Service } from '../interfaces/Services'; // Ensure the correct path to the Service interface

export const renderInputField = (field: Service['formFields'][number]) => {
  switch (field.fieldTypeName) {
    case 'text':
      return <input type="text" required={field.required} />;
    case 'email':
      return <input type="email" required={field.required} />;
    case 'tel':
      return <input type="tel" required={field.required} />;
    case 'file':
      return <input type="file" required={field.required} />;
    case 'dropdown':
      return (
        <select required={field.required}>
          <option value="">Select an option</option>
          <option value="Option 1">Option 1</option>
          <option value="Option 2">Option 2</option>
        </select>
      );
    case 'checkbox':
      return <input type="checkbox" required={field.required} />;
    default:
      return <input type="text" required={field.required} />;
  }
};
