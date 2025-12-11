export const routes = {
  login: "/login",
  register: "/register",
  dashboard: "/dashboard",
  patients: "/patients",
  patientDetail: (id = ":id") => `/patients/${id}`,
  patientAdd: "/patients/add",
  appointments: "/appointments",
  calendar: "/calendar",
  dossiers: "/dossiers",
};

export const navLinks = [
  { label: "Dashboard", to: routes.dashboard },
  { label: "Patients", to: routes.patients },
  { label: "Appointments", to: routes.appointments },
  { label: "Dossiers", to: routes.dossiers },
];

export const authLinks = [
  { label: "Login", to: routes.login },
  { label: "Register", to: routes.register },
];
