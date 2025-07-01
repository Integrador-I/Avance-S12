import BuscadorViaje from "../../BuscarViajes/buscardoViaje"
export const EntradaPage = () => {
    return (
        <section
            className="relative z-40 overflow-x-hidden h-screen flex items-center bg-cover bg-center bg-no-repeat"
            style={{ backgroundImage: "url('/images/PortadaArequipa.webp')" }}
        >
            <div className="absolute inset-0 bg-gradient-to-b from-blue-900/50 via-blue-800/40 to-black/60 z-0" />
            <div className="relative z-10 px-4 mx-auto max-w-7xl w-full overflow-x-hidden">
                <div className="flex flex-col lg:flex-row items-center justify-between gap-10 lg:gap-16">
                    <div className="w-full lg:w-1/2 text-center lg:text-left space-y-6">
                        <h2 className="text-4xl sm:text-5xl font-bold leading-tight text-white pb-3 drop-shadow-lg">
                            TRANSPORTE SEGURO<br />
                            <span className="text-blue-300">ENCOMIENDAS SEGURAS</span>
                        </h2>
                    </div>
                </div>
                <BuscadorViaje />
            </div>
        </section>
    )
}
