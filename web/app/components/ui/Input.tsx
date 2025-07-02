import { InputHTMLAttributes } from 'react';

export default function Input({
  value,
  placeholder,
  onChange,
  type,
  id,
}: InputHTMLAttributes<HTMLInputElement>) {
  return (
    <input
      id={id}
      type={type || 'text'}
      className="w-full border border-gray-300 rounded-md focus:outline-none p-2"
      placeholder={placeholder || ''}
      value={value}
      onChange={onChange}
      required
    />
  );
}
