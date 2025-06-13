"use client";

import React, { useState } from "react";
import { BotonPaquetes } from "./botonPaquetes";
import Image from "next/image";

export const Paquetes = () => {
  const [indiceActual, setIndiceActual] = useState(0);
  const [tipoPaquete, setTipoPaquete] = useState("Sobre A4");

  const [remitente, setRemitente] = useState({
    remitenteDNI: "",
    remitenteNombre: "",
    remitenteApellidoPaterno: "",
    remitenteApellidoMaterno: "",
    remitenteCelular: ""
  });

  const [destinatario, setDestinatario] = useState({
    destinatarioDNI: "",
    destinatarioNombre: "",
    destinatarioApellidoPaterno: "",
    destinatarioApellidoMaterno: "",
    destinatarioCelular: ""
  });

  const [mensajeCodigo, setMensajeCodigo] = useState(""); // NUEVO

  const paquetes = [
    {
      talla: "Sobre A4",
      detalle: `Documentos simples en sobre manila: <br><br>Tamaño<br><strong>A4</strong>`,
      imagen: "/images/foto.png"
    },
    {
      talla: "Caja S",
      detalle: `Las dimensiones del paquete deben estar dentro del rango de: <br><br>10 x 20 x 15 cm<br><strong>Peso máx. 2 kg</strong>`,
      imagen: "/images/foto.png"
    },
    {
      talla: "Caja M",
      detalle: `Las dimensiones del paquete deben estar dentro del rango de: <br><br>24 x 30 x 20 cm<br><strong>Peso máx. 5 kg</strong>`,
      imagen: "/images/foto.png"
    },
    {
      talla: "Caja XL",
      detalle: `Las dimensiones del paquete deben estar dentro del rango de: <br><br>40 x 50 x 30 cm<br><strong>Peso máx. 10 kg</strong>`,
      imagen: "/images/foto.png"
    },
    {
      talla: "Caja XXL",
      detalle: `Las dimensiones del paquete deben estar dentro del rango de: <br><br>50 x 60 x 40 cm<br><strong>Peso máx. 15 kg</strong>`,
      imagen: "/images/foto.png"
    }
  ];

  const CambiarPaquete = () => {
    const nuevoIndice = (indiceActual + 1) % paquetes.length;
    setIndiceActual(nuevoIndice);
    setTipoPaquete(paquetes[nuevoIndice].talla);
  };

  const handleSubmit = async () => {
    const datosCompletos = {
      ...remitente,
      ...destinatario,
      tipoPaquete: tipoPaquete
    };

    try {
      const res = await fetch("http://localhost:8080/encomiendas", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(datosCompletos)
      });

      const responseText = await res.text(); // texto plano desde el backend

      if (res.ok) {
        setMensajeCodigo(responseText); // guardar mensaje recibido

        const mensaje = document.getElementById("registroMensaje");
        if (mensaje) {
          mensaje.classList.remove("hidden");

          setTimeout(() => {
            mensaje.classList.add("hidden");
            setMensajeCodigo(""); // limpia mensaje
            window.location.reload(); // recarga para limpiar formulario
          }, 3000);
        }
      } else {
        alert("Error al registrar:\n" + responseText);
      }
    } catch (error) {
      alert("Error en la conexión con el servidor");
    }
  };

  const camposRemitente = [
    { label: "DNI", key: "remitenteDNI", type: "number" },
    { label: "Nombre completo", key: "remitenteNombre", type: "text" },
    { label: "Apellido Paterno", key: "remitenteApellidoPaterno", type: "text" },
    { label: "Apellido Materno", key: "remitenteApellidoMaterno", type: "text" },
    { label: "Celular", key: "remitenteCelular", type: "number" }
  ];

  const camposDestinatario = [
    { label: "DNI", key: "destinatarioDNI", type: "number" },
    { label: "Nombre completo", key: "destinatarioNombre", type: "text" },
    { label: "Apellido Paterno", key: "destinatarioApellidoPaterno", type: "text" },
    { label: "Apellido Materno", key: "destinatarioApellidoMaterno", type: "text" },
    { label: "Celular", key: "destinatarioCelular", type: "number" }
  ];

  return (
    <div className="flex flex-col items-center w-full max-w-7xl px-4 py-6">
      <div className="text-center mb-6">
        <h1 className="text-4xl text-amber-600 font-bold mb-2">
          ¿Qué tipo de producto deseas enviar?
        </h1>
        <p className="font-bold text-[#838383]">Elige un paquete</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 w-full">
        <div className="p-5 bg-white text-black rounded-[20px] overflow-hidden flex flex-col shadow-md">
          <h3 className="font-light opacity-90">Elija el paquete</h3>
          <h2 className="font-bold">Producto a enviar</h2>
          <h2 className="font-bold">{paquetes[indiceActual].talla}</h2>
          <div
            className="mt-4 opacity-90"
            dangerouslySetInnerHTML={{ __html: paquetes[indiceActual].detalle }}
          />
          <Image
            src={paquetes[indiceActual].imagen}
            alt="Imagen Paquete"
            width={500}
            height={300}
            className="w-full h-auto object-contain mt-4"
          />
          <BotonPaquetes onClick={CambiarPaquete} />
        </div>

        <div className="p-5 bg-white text-black rounded-[20px] overflow-hidden flex flex-col shadow-md">
          <h3 className="font-light opacity-90">¿Quién realiza el envío?</h3>
          <div className="mt-4 opacity-90 space-y-4">
            {camposRemitente.map(({ label, key, type }) => (
              <div key={key}>
                <strong>{label}</strong>
                <input
                  type={type}
                  placeholder={label}
                  value={remitente[key as keyof typeof remitente]}
                  onChange={(e) =>
                    setRemitente({
                      ...remitente,
                      [key]: e.target.value
                    })
                  }
                  className="w-full p-2 border rounded mt-1"
                />
              </div>
            ))}

            {/* Selector de tipo de paquete */}
            <div>
              <strong>Tipo de paquete</strong>
              <select
                className="w-full p-2 border rounded mt-1"
                value={tipoPaquete}
                onChange={(e) => {
                  const nuevoTipo = e.target.value;
                  setTipoPaquete(nuevoTipo);
                  const nuevoIndice = paquetes.findIndex(p => p.talla === nuevoTipo);
                  if (nuevoIndice !== -1) setIndiceActual(nuevoIndice);
                }}
              >
                {paquetes.map((p) => (
                  <option key={p.talla} value={p.talla}>
                    {p.talla}
                  </option>
                ))}
              </select>
            </div>
          </div>
        </div>

        <div className="p-5 bg-white text-black rounded-[20px] overflow-hidden flex flex-col shadow-md">
          <h3 className="font-light opacity-90">¿Quién recibe el envío?</h3>
          <div className="mt-4 opacity-90 space-y-4">
            {camposDestinatario.map(({ label, key, type }) => (
              <div key={key}>
                <strong>{label}</strong>
                <input
                  type={type}
                  placeholder={label}
                  value={destinatario[key as keyof typeof destinatario]}
                  onChange={(e) =>
                    setDestinatario({
                      ...destinatario,
                      [key]: e.target.value
                    })
                  }
                  className="w-full p-2 border rounded mt-1"
                />
              </div>
            ))}

            <button
              onClick={handleSubmit}
              className="w-full bg-[#3b5998] text-white py-3 px-4 rounded-[10px] text-base font-bold transition-all duration-200 hover:scale-[1.02]"
            >
              Continuar
            </button>

            <div
              id="registroMensaje"
              className="hidden mt-4 font-bold text-white text-center fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-[#3b5998] py-5 px-10 rounded-[10px] shadow-[0_0_20px_rgba(0,0,0,0.3)] text-xl z-[999] w-auto min-w-[250px] max-w-[500px]"
            >
              {mensajeCodigo || "Se ha registrado correctamente"}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
