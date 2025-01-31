import React from 'react';
import { Service } from '../interfaces/Services';

export const renderInputField = (field: Service['formFields'][number]) => {
  const { fieldTypeId, fieldName, required, fieldOptions } = field;

  switch (fieldTypeId) {
    case 1: // Text field
      return <input type="text" placeholder={fieldName} required={required} />;
    case 2: // Email field
      return <input type="email" placeholder={fieldName} required={required} />;
    case 3: // Password field
      return <input type="password" placeholder={fieldName} required={required} />;
    case 4: // Number field
      return <input type="number" placeholder={fieldName} required={required} />;
    case 5: // Date field
      return <input type="date" placeholder={fieldName} required={required} />;
    case 6: // DateTime Local field
      return <input type="datetime-local" placeholder={fieldName} required={required} />;
    case 7: // Time field
      return <input type="time" placeholder={fieldName} required={required} />;
    case 8: // Month field
      return <input type="month" placeholder={fieldName} required={required} />;
    case 9: // Week field
      return <input type="week" placeholder={fieldName} required={required} />;
    case 10: // Radio button
      return (
        <label>
          <input type="radio" required={required} />
          {fieldName}
        </label>
      );
    case 11: // Dropdown field
      const options = fieldOptions ? JSON.parse(fieldOptions).options : [];
      return (
        <select required={required}>
          <option value="">Select an option</option>
          {options.map((option: string, index: number) => (
            <option key={index} value={option}>
              {option}
            </option>
          ))}
        </select>
      );
    case 12: // Checkbox
      return (
        <label>
          <input type="checkbox" required={required} />
          {fieldName}
        </label>
      );
    case 13: // Textarea
      return <textarea placeholder={fieldName} required={required}></textarea>;
    case 14: // File input
      return <input type="file" required={required} />;
    case 15: // Hidden input
      return <input type="hidden" />;
    case 16: // Range input
      return <input type="range" required={required} />;
    case 17: // Tel input
      return <input type="tel" placeholder={fieldName} required={required} />;
    case 18: // URL input
      return <input type="url" placeholder={fieldName} required={required} />;
    case 19: // Color input
      return <input type="color" required={required} />;
    case 20: // Search input
      return <input type="search" placeholder={fieldName} required={required} />;
    default:
      return <input type="text" placeholder={fieldName} required={required} />;
  }
};
