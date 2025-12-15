import React from "react";
import data from "./info";

export const MissionSection = () => {
  return (
    <div className="w-[90%] mx-auto px-4 py-16">
      {data.map((info, idx) => (
        <div key={idx} className={`grid md:grid-cols-2 gap-14 items-center mb-12 ${idx % 2 === 1 ? 'md:col-reverse' : ''}`}>
          <div>
            <h2 className="text-4xl font-bold text-gray-800 mb-6 underline">
              {info.title}
            </h2>
            <p className="text-gray-700 text-lg mb-4">{info.desc}</p>
          </div>
          <div className={`overflow-hidden rounded-lg shadow-lg ${idx % 2 === 1 ? 'md:order-first' : ''}`}>
            <img src={info.image} alt={info.title} className="w-full h-96 object-cover" />
          </div>
        </div>
      ))}
    </div>
  );
};

export default MissionSection;
