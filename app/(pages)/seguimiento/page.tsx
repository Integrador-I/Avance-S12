import { EntradaEncomienda } from "@/components/Encomiendas/entrada";

export default function encomiendas() {
  return (
    <div className="bg-blue-50 min-h-screen">
      {/* Hero Section */}
      <div className="bg-gradient-to-r from-blue-400 to-blue-600 text-white py-16">
        <div className="container mx-auto px-4 text-center">
          <h1 className="text-4xl font-bold mb-4">Seguimiento de Encomiendas</h1>
          <p className="text-xl mb-8 text-blue-100">Busca y consulta el estado de tu encomienda</p>
        </div>
      </div>

      {/* Contenido principal */}
      <div className="container mx-auto px-4 py-12">
        <EntradaEncomienda />
      </div>
    </div>
  )
}
