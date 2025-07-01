"use client";

import { CalendarioFecha } from "./CalendarioFecha";
import Link from "next/link";
import { SelectDemo } from "./selectionRuta";



export default function BuscadorViaje() {
    return (
        <div className="bg-white/95 backdrop-blur-sm rounded-2xl shadow-2xl border border-blue-100 p-6 max-w-5xl mx-auto">
            <div className="flex flex-wrap items-center justify-between gap-4">
                <div className="flex items-center gap-3 min-w-0 flex-1">
                    <div className="p-2 bg-blue-100 rounded-full">
                        <i className="text-blue-600 text-xl">🚌</i>
                    </div>
                    <div className="min-w-0 flex-1">
                        <p className="text-sm font-medium text-blue-700 mb-1">Desde</p>
                        <SelectDemo/>
                    </div>
                </div>

                <div className="flex items-center gap-3 min-w-0 flex-1 border-l border-blue-200 pl-4">
                    <div className="p-2 bg-blue-100 rounded-full">
                        <i className="text-blue-600 text-xl">🚌</i>
                    </div>
                    <div className="min-w-0 flex-1">
                        <p className="text-sm font-medium text-blue-700 mb-1">Hasta</p>
                        <SelectDemo/>
                    </div>
                </div>
                
                <div className="flex items-center gap-3 min-w-0 flex-1 border-l border-blue-200 pl-4">
                    <div className="p-2 bg-blue-100 rounded-full">
                        <i className="text-blue-600 text-xl">📅</i>
                    </div>
                    <div className="min-w-0 flex-1">
                        <p className="text-sm font-medium text-blue-700 mb-1">Fecha de ida</p>
                        <CalendarioFecha label="Fecha de ida" />
                    </div>
                </div>
                
                <div className="flex items-center gap-3 min-w-0 flex-1 border-l border-blue-200 pl-4">
                    <div className="p-2 bg-blue-100 rounded-full">
                        <i className="text-blue-600 text-xl">📅</i>
                    </div>
                    <div className="min-w-0 flex-1">
                        <p className="text-sm font-medium text-blue-700 mb-1">Fecha de vuelta</p>
                        <CalendarioFecha label="Fecha de vuelta" optional />
                    </div>
                </div>
                
                <div className="flex-shrink-0">
                    <Link href="/viajes">
                        <button className="bg-blue-600 hover:bg-blue-700 text-white font-bold px-8 py-3 rounded-full transition-colors duration-200 shadow-lg hover:shadow-xl transform hover:scale-105">
                            BUSCAR
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
}
