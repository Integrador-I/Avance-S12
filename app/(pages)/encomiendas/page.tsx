import { Paquetes } from "@/components/Paquetes/Paquetes";

export default function Encomiendas() {
    return (
        <div className="bg-blue-50 min-h-screen">
            {/* Hero Section */}
            <div className="bg-gradient-to-r from-blue-400 to-blue-600 text-white py-16">
                <div className="container mx-auto px-4 text-center">
                    <h1 className="text-4xl font-bold mb-4">Paquetería</h1>
                    <p className="text-xl mb-8 text-blue-100">Envía tus paquetes de forma segura y confiable</p>
                </div>
            </div>

            {/* Contenido principal */}
            <div className="container mx-auto px-4 py-12">
                <Paquetes />
            </div>
        </div>
    )
}
