import React, { useEffect, useState } from "react";
import OverviewCards from "../../../components/dashboard/patients/overview/OverviewCards";
import { LastAppointment } from "../../../components/dashboard/patients/overview/LastAppointment";
import MessageFromDoc from "../../../components/dashboard/patients/overview/MessageFromDoc";
import HistGraph from "../../../components/dashboard/patients/overview/BarGraph";
import LinearGraph from "../../../components/dashboard/patients/overview/LinearGraph";

export const OverviewPage = () => {
  const [doctorInfo, setDoctorInfo] = useState({
    // default doctor info (how it will looks like after fetching from backend API)
    name: "Dr. Mouaad Bennani",
    message:
      "Your recent blood tests show excellent results. Continue with your current medication and schedule a follow-up in 3 months.",
    patientInfo: [
      {
        label: "Blood Pressure",
        value: "120/80   ",
        status: "Normal",
        unite: "mmHg",
      },
      { label: "Heart Rate", value: "72", status: "Normal", unite: "bpm" },
      { label: "Temperature", value: "37.2", status: "Normal", unite: "°C" },
      { label: "BMI", value: "22.5", status: "Healthy" },
    ],
  });

  // the default overview data (to be fetched from backend API later)

  const [overviewData, setOverviewData] = useState([
    { id: "total-appointments", label: "Total Appointments", value: 12 },
    { id: "total-asking", label: "Pending Consultations", value: 3 },
    { id: "last-appointment", label: "Last Appointment", value: "2024-12-15" },
    { id: "next-appointment", label: "Next Appointment", value: "2025-01-10" },
  ]);

  useEffect(() => {
    // TODO: fetch the doctor info from backend API and update state
    // TODO: fetch the overview data from backend API and update state
  }, []);


  // After fetching data from backend API, we will prepare data for charts
  const chartsData = [
    {
      labels: doctorInfo.patientInfo.map((item) => item.label),
      data: doctorInfo.patientInfo.map((item) => item.value),
    },
  ];

  return (
    <div className="min-h-screen bg-linear-to-br from-slate-50 via-blue-50 to-white px-4 sm:px-6 lg:px-8 py-8 lg:py-12">
      
      {/* ====== HEADER SECTION ====== */}
      <div className="mb-12">
        <h1 className="text-5xl lg:text-6xl font-bold text-slate-900 mb-3">
          Patient Overview
        </h1>
        <div className="w-1.5 h-1.5 bg-blue-600 rounded-full mb-4"></div>
        <p className="text-lg text-slate-600 max-w-2xl">
          Welcome back! Here's your comprehensive health summary and latest medical information
        </p>
      </div>

      {/* ====== OVERVIEW CARDS SECTION ====== */}
      <section className="mb-16">
        <OverviewCards overviewData={overviewData} />
      </section>

      {/* ====== HEALTH METRICS SECTION ====== */}
      <section className="mb-16">
        <div className="mb-8">
          <h2 className="text-3xl lg:text-4xl font-bold text-slate-900 mb-2">
            Recent Health Metrics
          </h2>
          <div className="w-20 h-1 bg-linear-to-r from-blue-600 to-blue-400 rounded-full"></div>
        </div>
        
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          {doctorInfo.patientInfo.map((metric, idx) => (
            <div
              key={idx}
              className="bg-white rounded-xl shadow-md hover:shadow-lg transition-shadow duration-300 p-6 border-l-4 border-blue-500"
            >
              <p className="text-slate-600 text-sm font-semibold uppercase tracking-wide">
                {metric.label}
              </p>
              <div className="mt-4 flex items-baseline gap-1">
                <p className="text-3xl font-bold text-slate-900">
                  {metric.value}
                </p>
                {metric.unite && (
                  <span className="text-sm font-medium text-slate-600">{metric.unite}</span>
                )}
              </div>
              <div className="mt-3 flex items-center gap-1">
                <span className="text-green-600 font-bold">✓</span>
                <p className="text-green-600 text-xs font-semibold">{metric.status}</p>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* ====== APPOINTMENTS & MESSAGES SECTION ====== */}
      <section className="mb-16">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <LastAppointment />
          <MessageFromDoc doctorInfo={doctorInfo} />
        </div>
      </section>

      {/* ====== QUICK ANALYSIS SECTION ====== */}
      <section>
        <div className="mb-8">
          <h2 className="text-3xl lg:text-4xl font-bold text-slate-900 mb-2">
            Quick Analysis
          </h2>
          <div className="w-20 h-1 bg-linear-to-r from-blue-600 to-blue-400 rounded-full"></div>
        </div>
        
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          {chartsData.length > 0 && (
            <>
              <div className="bg-white rounded-xl shadow-md p-6">
                <h3 className="text-lg font-semibold text-slate-900 mb-4">Health Metrics Overview</h3>
                <HistGraph data={chartsData[0]} />
              </div>
              <div className="bg-white rounded-xl shadow-md p-6">
                <h3 className="text-lg font-semibold text-slate-900 mb-4">Trends Analysis</h3>
                <LinearGraph data={chartsData[0]} />
              </div>
            </>
          )}
        </div>
      </section>

    </div>
  );
};

export default OverviewPage;
