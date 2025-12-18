import React from "react";
import reserve from "../../../../assets/images/reserve.jpg";
import { Bed, FileX, LetterText, Phone } from "lucide-react";

export default function ReserveHero() {
  const styling = `
        .hero{
            background: linear-gradient(130deg, rgba(0, 0, 0, .4) 0%, rgba(0, 0, 0, .5) 100%), url(${reserve}) center / cover no-repeat;
            background-attachment: fixed;
            display:flex;
            align-items: center;
        }
    `;

  const stats = [
    { icon: Bed, label: "ECG Machine", value: "5K+ / Day" },
    { icon: Phone, label: "Laboratory Analyzer", value: "500+" },
    { icon: LetterText, label: "Cardiac Monitor", value: "15+" },
    { icon: FileX, label: "Innovation", value: "100%" },
  ];

  return (
    <div>
      <style>{styling}</style>
      <div className="hero h-screen p-16 text-white">
        <div className="max-w-6xl mx-auto px-4 md:px-6 w-full">
          <h1 className="text-5xl md:text-7xl font-bold mb-4 drop-shadow-2xl">
            Reserve Your Appointment
          </h1>
          <p className="text-xl md:text-2xl text-blue-50 font-light mb-8 max-2xl">
            Innovation, Education, and Compassion at the Heart of Healthcare
            Technology
          </p>
          <p className="text-lg text-blue-100 mb-12 max-w-3xl">
            ENSA Hospital is a modern healthcare institution committed to
            delivering high-quality medical services while fostering innovation,
            research, and education. We combine medical expertise with
            technological advancement to improve patient care and healthcare
            systems.
          </p>
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
            {stats.map((stat, idx) => {
              const Icon = stat.icon;
              return(
                <div
                key={idx}
                className="border border-white bg-opacity-10 backdrop-blur-xs rounded-2xl p-6 text-center"
              >
                <Icon className="w-10 h-10 text-white mx-auto mb-3" />
                <p className="text-blue-100">{stat.value}</p>
                <p className="text-sm text-blue-50 font-semibold">{stat.label}</p>
              </div>
              )
            })}
          </div>
        </div>
      </div>
    </div>
  );
}
