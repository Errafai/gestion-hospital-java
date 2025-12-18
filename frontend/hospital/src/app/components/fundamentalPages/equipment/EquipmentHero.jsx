import React from "react";
import Equipment from "../../../../assets/images/equipments.jpg";
import { Activity, Beaker, Zap, Heart } from "lucide-react";

export const EquipmentHero = () => {
  const styling = `
      .hero{
        background: linear-gradient(130deg, rgba(0, 0, 0, .4) 0%, rgba(0, 0, 0, .5) 100%), url(${Equipment}) center / cover no-repeat;
        background-attachment: fixed;
        position:relative;
        display:flex;
        align-items: center;
      }
  
      .hero::before{
        content:'';
        position:absolute;
        top-0;
        left:0;
        right:0;
        // background: linear-gradient(135deg, rgba(79, 70, 229, 0.4) 0%, rgba(59, 130, 246, 0.3) 100%);
        pointer-events:none;
      }
  
      hero > div {
        position: relative;
        z-index: 10;
      }
    `;

  const stats = [
    { icon: Activity, label: "ECG Machine", value: "5K+ / Day" },
    { icon: Beaker, label: "Laboratory Analyzer", value: "500+" },
    { icon: Heart, label: "Cardiac Monitor", value: "15+" },
    { icon: Zap, label: "Innovation", value: "100%" }
  ];
  return (
    <div>
      <style>{styling}</style>
      <div className="hero text-white py-16 h-screen">
        <div className="max-w-6xl mx-auto px-4 md:px-6 w-full">
          <h1 className="text-5xl md:text-7xl font-bold mb-4 text-white drop-shadow-lg">
            Our Advanced Medical Equipment
          </h1>
          <p className="text-xl md:text-2xl text-blue-50 font-light mb-8 max-w-2xl">
            Innovation, Education, and Compassion at the Heart of Healthcare Technology
          </p>
          <p className="text-lg text-blue-100 mb-12 max-w-3xl">
            ENSA Hospital is a modern healthcare institution committed to
            delivering high-quality medical services while fostering innovation,
            research, and education. We combine medical expertise with
            technological advancement to improve patient care and healthcare
            systems.
          </p>

          <div className="grid grid-cols-2 md:grid-cols-4 gap-6 mb-8">
            {stats.map((stat, idx) => {
              const Icon = stat.icon;
              return (
                <div
                  key={idx}
                  className="border border-white bg-opacity-10 backdrop-blur-md rounded-lg p-6 text-center hover:bg-opacity-20 transition"
                >
                  <Icon className="w-8 h-8 text-white mx-auto mb-3" />
                  <p className="text-3xl font-bold text-white mb-2">
                    {stat.value}
                  </p>
                  <p className="text-blue-100 text-sm">{stat.label}</p>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default EquipmentHero;
