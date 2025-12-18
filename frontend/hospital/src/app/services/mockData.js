// Mock Doctors Data
export const mockDoctors = [
  {
    id: 1,
    name: "Dr. Ahmed Hassan",
    specialty: "General Practitioner",
    image: "https://via.placeholder.com/150",
    available: true,
  },
  {
    id: 2,
    name: "Dr. Fatima El-Moussaoui",
    specialty: "Cardiologist",
    image: "https://via.placeholder.com/150",
    available: true,
  },
  {
    id: 3,
    name: "Dr. Mohammed Belaid",
    specialty: "Pediatrician",
    image: "https://via.placeholder.com/150",
    available: true,
  },
  {
    id: 4,
    name: "Dr. Yasmine Aziz",
    specialty: "Dermatologist",
    image: "https://via.placeholder.com/150",
    available: true,
  },
  {
    id: 5,
    name: "Dr. Hassan Mansouri",
    specialty: "Orthopedic Surgeon",
    image: "https://via.placeholder.com/150",
    available: true,
  },
];

// Mock Appointment Types
export const appointmentTypes = [
  { id: 1, name: "General Consultation", duration: 30 },
  { id: 2, name: "Follow-up Visit", duration: 20 },
  { id: 3, name: "Emergency", duration: 15 },
  { id: 4, name: "Routine Check-up", duration: 30 },
];

// Available Time Slots
export const timeSlots = [
  "09:00",
  "09:30",
  "10:00",
  "10:30",
  "11:00",
  "11:30",
  "14:00",
  "14:30",
  "15:00",
  "15:30",
  "16:00",
  "16:30",
];

// Mock existing appointments (to check availability)
export const mockAppointments = [
  {
    id: 1,
    patientName: "John Doe",
    doctorId: 1,
    appointmentDate: "2025-12-20",
    time: "09:00",
    status: "confirmed",
    type: "General Consultation",
  },
  {
    id: 4,
    patientName: "John Doe",
    doctorId: 3,
    appointmentDate: "2025-12-20",
    time: "09:00",
    status: "completed",
    type: "General Consultation",
  },
  {
    id: 2,
    patientName: "Jane Smith",
    doctorId: 1,
    appointmentDate: "2025-12-20",
    time: "10:00",
    status: "confirmed",
    type: "Follow-up Visit",
  },
  {
    id: 3,
    patientName: "Ali Khan",
    doctorId: 2,
    appointmentDate: "2025-12-21",
    time: "11:00",
    status: "confirmed",
    type: "General Consultation",
  },
  {
    id: 5,
    patientName: "John Doe",
    doctorId: 1,
    appointmentDate: "2025-12-20",
    time: "09:00",
    status: "pending",
    type: "General Consultation",
  },
];

// Get available time slots for a specific doctor and date
export const getAvailableSlots = (doctorId, date) => {
  const bookedSlots = mockAppointments
    .filter((apt) => apt.doctorId === doctorId && apt.appointmentDate === date)
    .map((apt) => apt.time);

  return timeSlots.filter((slot) => !bookedSlots.includes(slot));
};

// Get doctor by ID
export const getDoctorById = (id) => {
  return mockDoctors.find((doctor) => doctor.id === parseInt(id));
};
