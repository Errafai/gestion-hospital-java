export const formatDate = (date) => {
  if (!date) return "";
  return new Date(date).toLocaleDateString("en-US");
};

export const formatDateTime = (dateTime) => {
  if (!dateTime) return "";
  return new Date(dateTime).toLocaleString("en-US");
};

export const formatCurrency = (amount) => {
  return new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
  }).format(amount);
};

export const formatPhoneNumber = (phone) => {
  if (!phone) return "";
  return phone.replace(/(\d{3})(\d{3})(\d{4})/, "($1) $2-$3");
};

export default {
  formatDate,
  formatDateTime,
  formatCurrency,
  formatPhoneNumber,
};
