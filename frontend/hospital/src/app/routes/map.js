export const routes = {
  home: "/",
  login: "/login",
  register: "/register",
  dashboard: "/patients/dashboard",
  profile: '/patient/profile',
  patients: "/patients",
  patientDetail: (id = ":id") => `/patients/${id}`,
  patientAdd: "/patients/add",
  docAppointments: "/appointments",
  patAppointments: "/appointments",
  calendar: "/calendar",
  dossiers: "/dossiers",
  aboutUs: "/about-us",
  equipments: "/equipments",
  contactUs: "/contact-us",
  reserve: "/patients/reserve",
};

export const navLinks = [
  // { label: "Home", to: routes.home },
  { label: "About Us", to: routes.aboutUs },
  { label: "Equipments", to: routes.equipments },
  { label: "Contact Us", to: routes.contactUs },
  // { label: "Appointments", to: routes.appointments},
  // { label: "Reserve", to: routes.reserve},
];

export const navDashboard = [
  { label: "Profile", to: routes.profile },
  { label: "Log out", to: routes.login },
];

export const authLinks = [
  { label: "Login", to: routes.login },
  { label: "Register", to: routes.register },
];

export const DashboardAdminLinks = [
  { label: "Overview", to: routes.dashboard },
  { label: "Patients", to: routes.patients },
  { label: "Appointments", to: routes.docAppointments },
  { label: "Reserve", to: routes.reserve },
  { label: "Dossiers", to: routes.dossiers },
];


export const DashboardPatientsLinks = [
  { label: "Overview", to: routes.dashboard },
  { label: "Appointments", to: routes.patAppointments },
  { label: "Reserve", to: routes.reserve },
  { label: "Dossiers", to: routes.dossiers },
];
