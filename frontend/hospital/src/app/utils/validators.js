export const validateEmail = (email) => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
};

export const validatePassword = (password) => {
  return password.length >= 8;
};

export const validatePhone = (phone) => {
  const phoneRegex = /^[\d\s\-\(\)]+$/;
  return phoneRegex.test(phone);
};

export const validateForm = (formData, rules) => {
  const errors = {};
  Object.keys(rules).forEach((field) => {
    if (!formData[field]) {
      errors[field] = `${field} is required`;
    }
  });
  return errors;
};

export default {
  validateEmail,
  validatePassword,
  validatePhone,
  validateForm,
};
