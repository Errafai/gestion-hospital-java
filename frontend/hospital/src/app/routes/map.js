export const routes = {
  home: "/",
  login: "/login",
  register: "/register",
  dashboard: "/patients/dashboard",
  patients: "/patients",
  patientDetail: (id = ":id") => `/patients/${id}`,
  patientAdd: "/patients/add",
  appointments: "/appointments",
  calendar: "/calendar",
  dossiers: "/dossiers",
  aboutUs: "/about-us",
  equipments: "/equipments",
  contactUs: "/contact-us",
  reserve: "/reserve",
};

export const navLinks = [
  // { label: "Home", to: routes.home },
  { label: "About Us", to: routes.aboutUs },
  { label: "Equipments", to: routes.equipments },
  { label: "Contact Us", to: routes.contactUs },
  { label: "Appointments", to: routes.appointments},
  { label: "Reserve", to: routes.reserve},
];

export const authLinks = [
  { label: "Login", to: routes.login },
  { label: "Register", to: routes.register },
];

export const DashboardLinks = [
  { label: "Overview", to: routes.dashboard },
  { label: "Patients", to: routes.patients },
  { label: "Appointments", to: routes.appointments },
  { label: "Dossiers", to: routes.dossiers },
];
