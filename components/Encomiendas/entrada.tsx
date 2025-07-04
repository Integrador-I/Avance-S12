"use client";
import Image from "next/image";
import { useState, useEffect } from "react";
import { EstadoDePedidoRecomendado } from "./estadoEncomienda";

export const EntradaEncomienda = () => {
  const [searchPressed, setSearchPressed] = useState(false);
  const [mounted, setMounted] = useState(false);
  const [searchValue, setSearchValue] = useState("");
  const [estadoEncomienda, setEstado] = useState<"confirmed" | "paid" | "shipped" | "delivered" | null>(null);

  useEffect(() => {
    setMounted(true);
  }, []);

  const handleSearch = async (e: React.FormEvent) => {
    e.preventDefault();
    setSearchPressed(true);
    try {
      const res = await fetch(`http://localhost:8080/api/seguimiento/${searchValue}`);
      if (!res.ok) throw new Error("No se pudo obtener el estado");

      const data = await res.json();
      setEstado(data.estadoEncomienda);
    } catch (error) {
      console.log("Error al buscar estado: ", error)
      setEstado(null);
    }
  };

  return (
    
      <div className="px-4 mx-auto max-w-7xl w-full">
        <div
          className={`text-center mb-10 transition-all duration-700 ${mounted ? "animate-fadeIn" : "opacity-0"
            }`}
        >
          <h2 className="text-3xl sm:text-5xl font-bold leading-tight text-blue-800">
            BUSCA Y CONSULTA EL <br />
            <span className="text-blue-600">ESTADO</span>
            <br />
            DE TU ENCOMIENDA
          </h2>
        </div>

        <form
          className={`max-w-2xl mx-auto mb-12 transition-all duration-700 ${mounted ? "animate-fadeIn" : "opacity-0"
            }`}
          onSubmit={handleSearch}
        >
          <div className="relative flex shadow-lg rounded-lg overflow-hidden">
            <input
              type="search"
              id="codigo-encomienda"
              className="flex-1 p-4 pl-10 text-base text-gray-700 border border-gray-300 bg-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-500 rounded-l-lg"
              placeholder="Ingresa el código de tu encomienda"
              required
              value={searchValue}
              onChange={(e) => setSearchValue(e.target.value)}
            />
            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <svg
                className="w-5 h-5 text-gray-400"
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M21 21l-4.35-4.35m0 0A7.5 7.5 0 104.5 4.5a7.5 7.5 0 0012.15 12.15z"
                />
              </svg>
            </div>
            <button
              type="submit"
              className="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 transition duration-200 rounded-r-lg"
            >
              Buscar
            </button>
          </div>
        </form>


        <div className="grid grid-cols-1 md:grid-cols-2 gap-10 md:items-center">

          <div
            className={`relative aspect-square w-full max-w-sm mx-auto md:mx-0 rounded-2xl overflow-hidden shadow-xl transition-all duration-1000 ease-out ${mounted ? "animate-fadeIn" : "opacity-0"
              }`}
          >
            <Image
              src="/images/imgDeliverry.jpg"
              width={500}
              height={500}
              alt="Encomienda en tránsito"
              className="object-cover cursor-pointer "
              priority
              
            />
          </div>

          {searchPressed && estadoEncomienda && (
            <div className="space-y-10 transition-all duration-700 animate-pop">
              <EstadoDePedidoRecomendado status={estadoEncomienda} />
              <p className="text-blue-600 text-lg font-medium text-center md:text-left">
                Tu pedido se encuentra en tránsito y será entregado pronto.
              </p>
            </div>
          )}
        </div>
      </div>
  );
};