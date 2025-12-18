import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Navbar from './app/components/layout/Navbar'
import Footer from './app/components/layout/Footer'
import { routes } from './app/routes/map'
import LoginPage from './app/pages/auth/LoginPage'
import RegisterPage from './app/pages/auth/RegisterPage'
import DashboardPage from './app/pages/dashboard/DashboardPage'
import PatientsPage from './app/pages/patients/PatientsPage'
import PatientDetailPage from './app/pages/patients/PatientDetailPage'
import AddPatientPage from './app/pages/patients/AddPatientPage'
import AppointmentsPage from './app/pages/appointments/AppointmentsPage'
// import CalendarPage from './app/pages/appointments/CalendarPage'
import DossierPage from './app/pages/dossiers/DossierPage'
import { About } from './app/pages/home/About'
import { Equipment } from './app/pages/home/Equipment'
import { Contact } from './app/pages/home/Contact'
import HomePage from './app/pages/home/HomePage'
import Reserve from './app/pages/home/Reserve'

function App() {
  return (
    <Router>
      <div className="min-h-screen flex flex-col">
        <Navbar />
        <main className="flex-grow">
          <Routes>
            <Route path={routes.home} element={<HomePage />} />
            <Route path={routes.login} element={<LoginPage />} />
            <Route path={routes.aboutUs} element={<About />} />
            <Route path={routes.equipments} element={<Equipment />} />
            <Route path={routes.contactUs} element={<Contact />} />
            <Route path={routes.register} element={<RegisterPage />} />
            <Route path={routes.dashboard} element={<DashboardPage />} />
            <Route path={routes.patients} element={<PatientsPage />} />
            <Route path={routes.patientDetail()} element={<PatientDetailPage />} />
            <Route path={routes.patientAdd} element={<AddPatientPage />} />
            <Route path={routes.appointments} element={<AppointmentsPage />} />
            {/* <Route path={routes.calendar} element={<CalendarPage />} /> */}
            <Route path={routes.dossiers} element={<DossierPage />} />
            <Route path={routes.reserve} element={<Reserve />} />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  )
}

export default App
