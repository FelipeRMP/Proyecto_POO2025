import SwiftUI

// MARK: - Domain models inspired by the Java logic
enum EmployeeRole: String, CaseIterable, Identifiable {
    case administrador = "Administrador"
    case recepcionista = "Recepcionista"

    var id: String { rawValue }
}

enum RoomType: String, CaseIterable, Identifiable {
    case simple = "Simple"
    case doble = "Doble"
    case suite = "Suite"

    var id: String { rawValue }
}

enum RoomStatus: String, CaseIterable, Identifiable {
    case disponible = "Disponible"
    case ocupada = "Ocupada"
    case enLimpieza = "En limpieza"

    var id: String { rawValue }
    var tint: Color {
        switch self {
        case .disponible: return .green
        case .ocupada: return .orange
        case .enLimpieza: return .blue
        }
    }
}

struct Employee: Identifiable {
    let id = UUID()
    var dni: String
    var nombres: String
    var apellidos: String
    var role: EmployeeRole
    var password: String
}

struct Guest: Identifiable {
    let id = UUID()
    var dni: String
    var telefono: String
    var nombres: String
    var apellidos: String
    var correo: String
}

struct AdditionalService: Identifiable, Hashable {
    let id = UUID()
    var nombre: String
    var precio: Double
}

struct Room: Identifiable {
    let id = UUID()
    var numero: Int
    var capacidadMaxima: Int
    var precioPorNoche: Double
    var tipo: RoomType
    var estado: RoomStatus
}

struct Reservation: Identifiable {
    let id = UUID()
    var huesped: Guest
    var habitacion: Room
    var servicios: [AdditionalService]
    var fechaInicio: Date
    var fechaFin: Date
    var checkIn: Bool

    var noches: Int {
        Calendar.current.dateComponents([.day], from: fechaInicio, to: fechaFin).day ?? 0
    }

    var total: Double {
        let alojamiento = Double(max(1, noches)) * habitacion.precioPorNoche
        let extras = servicios.reduce(0) { $0 + $1.precio }
        return alojamiento + extras
    }
}

// MARK: - Data source with sample information
final class HotelDataStore: ObservableObject {
    @Published var employees: [Employee]
    @Published var guests: [Guest]
    @Published var rooms: [Room]
    @Published var services: [AdditionalService]
    @Published var reservations: [Reservation]

    init() {
        employees = [
            Employee(dni: "0001", nombres: "Ana", apellidos: "Admin", role: .administrador, password: "admin123"),
            Employee(dni: "0002", nombres: "Rene", apellidos: "Recepcion", role: .recepcionista, password: "hotel123")
        ]

        guests = [
            Guest(dni: "74185296", telefono: "999999999", nombres: "Lucía", apellidos: "Vidal", correo: "lucia@example.com")
        ]

        services = [
            AdditionalService(nombre: "Desayuno buffet", precio: 49.90),
            AdditionalService(nombre: "Servicio al cuarto", precio: 25),
            AdditionalService(nombre: "Spa y sauna", precio: 120),
            AdditionalService(nombre: "Lavandería", precio: 15)
        ]

        rooms = [
            Room(numero: 101, capacidadMaxima: 2, precioPorNoche: 99.99, tipo: .simple, estado: .disponible),
            Room(numero: 102, capacidadMaxima: 4, precioPorNoche: 189.99, tipo: .doble, estado: .disponible),
            Room(numero: 201, capacidadMaxima: 2, precioPorNoche: 249.99, tipo: .suite, estado: .enLimpieza)
        ]

        reservations = []
    }

    func login(username: String, password: String) -> Employee? {
        employees.first { $0.nombres.lowercased() == username.lowercased() && $0.password == password }
    }

    func registrar(guest: Guest) { guests.append(guest) }

    func registrar(room: Room) { rooms.append(room) }

    func registrar(reservation: Reservation) { reservations.append(reservation) }

    func eliminarReserva(_ reservation: Reservation) {
        reservations.removeAll { $0.id == reservation.id }
    }
}

// MARK: - Views
struct ContentView: View {
    @StateObject private var store = HotelDataStore()
    @State private var activeEmployee: Employee?

    var body: some View {
        Group {
            if let activeEmployee {
                NavigationStack {
                    MainDashboard(store: store, employee: activeEmployee)
                        .navigationTitle("Hotel La POO")
                        .toolbar { Button("Cerrar sesión") { self.activeEmployee = nil } }
                }
            } else {
                LoginView { username, password in
                    activeEmployee = store.login(username: username, password: password)
                }
            }
        }
    }
}

struct LoginView: View {
    @State private var username = ""
    @State private var password = ""
    @State private var feedback = ""
    var onLogin: (String, String) -> Void

    var body: some View {
        VStack(spacing: 24) {
            Text("Sistema de Gestión Hotelera")
                .font(.largeTitle)
                .bold()
            VStack(alignment: .leading, spacing: 12) {
                TextField("Usuario", text: $username)
                    .textFieldStyle(.roundedBorder)
                SecureField("Contraseña", text: $password)
                    .textFieldStyle(.roundedBorder)
            }
            Button(action: { onLogin(username, password) }) {
                Label("Ingresar", systemImage: "arrow.right.circle")
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)

            if !feedback.isEmpty {
                Text(feedback).foregroundColor(.red)
            }
        }
        .padding()
    }
}

struct MainDashboard: View {
    @ObservedObject var store: HotelDataStore
    let employee: Employee

    var body: some View {
        TabView {
            RoomListView(store: store)
                .tabItem { Label("Habitaciones", systemImage: "bed.double") }

            GuestListView(store: store)
                .tabItem { Label("Huéspedes", systemImage: "person.3") }

            ReservationListView(store: store)
                .tabItem { Label("Reservas", systemImage: "calendar") }

            ReportsView(store: store)
                .tabItem { Label("Reportes", systemImage: "chart.bar.doc.horizontal") }
        }
        .toolbarTitleDisplayMode(.inline)
    }
}

struct RoomListView: View {
    @ObservedObject var store: HotelDataStore
    @State private var showingNewRoom = false

    var body: some View {
        List {
            ForEach(store.rooms) { room in
                HStack {
                    VStack(alignment: .leading) {
                        Text("Habitación \(room.numero) · \(room.tipo.rawValue)")
                            .font(.headline)
                        Text("Capacidad: \(room.capacidadMaxima) · S/ \(room.precioPorNoche, specifier: "%.2f")")
                            .font(.subheadline)
                            .foregroundStyle(.secondary)
                    }
                    Spacer()
                    Text(room.estado.rawValue)
                        .padding(8)
                        .background(room.estado.tint.opacity(0.15))
                        .foregroundStyle(room.estado.tint)
                        .clipShape(RoundedRectangle(cornerRadius: 8))
                }
            }
        }
        .navigationTitle("Habitaciones")
        .toolbar {
            Button(action: { showingNewRoom = true }) { Label("Agregar", systemImage: "plus") }
        }
        .sheet(isPresented: $showingNewRoom) {
            RoomForm { room in
                store.registrar(room: room)
            }
        }
    }
}

struct RoomForm: View {
    @Environment(\.dismiss) private var dismiss
    @State private var numero = ""
    @State private var capacidad = 1
    @State private var precio = 100.0
    @State private var tipo: RoomType = .simple
    @State private var estado: RoomStatus = .disponible

    var onSave: (Room) -> Void

    var body: some View {
        NavigationStack {
            Form {
                TextField("Número de habitación", text: $numero)
                    .keyboardType(.numberPad)
                Stepper("Capacidad: \(capacidad)", value: $capacidad, in: 1...6)
                Stepper(value: $precio, in: 50...400, step: 5) {
                    Text("Precio por noche: S/ \(precio, specifier: "%.2f")")
                }
                Picker("Tipo", selection: $tipo) {
                    ForEach(RoomType.allCases) { Text($0.rawValue).tag($0) }
                }
                Picker("Estado", selection: $estado) {
                    ForEach(RoomStatus.allCases) { Text($0.rawValue).tag($0) }
                }
            }
            .navigationTitle("Nueva habitación")
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Cancelar", action: dismiss.callAsFunction) }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Guardar") {
                        guard let numeroInt = Int(numero) else { return }
                        onSave(Room(numero: numeroInt, capacidadMaxima: capacidad, precioPorNoche: precio, tipo: tipo, estado: estado))
                        dismiss()
                    }
                }
            }
        }
    }
}

struct GuestListView: View {
    @ObservedObject var store: HotelDataStore
    @State private var showingForm = false

    var body: some View {
        List {
            ForEach(store.guests) { guest in
                VStack(alignment: .leading) {
                    Text("\(guest.nombres) \(guest.apellidos)")
                        .font(.headline)
                    Text("DNI: \(guest.dni) · Tel: \(guest.telefono)")
                        .foregroundStyle(.secondary)
                }
            }
        }
        .navigationTitle("Huéspedes")
        .toolbar { Button(action: { showingForm = true }) { Label("Agregar", systemImage: "plus") } }
        .sheet(isPresented: $showingForm) {
            GuestForm { store.registrar(guest: $0) }
        }
    }
}

struct GuestForm: View {
    @Environment(\.dismiss) private var dismiss
    @State private var dni = ""
    @State private var telefono = ""
    @State private var nombres = ""
    @State private var apellidos = ""
    @State private var correo = ""

    var onSave: (Guest) -> Void

    var body: some View {
        NavigationStack {
            Form {
                TextField("DNI", text: $dni)
                TextField("Teléfono", text: $telefono)
                TextField("Nombres", text: $nombres)
                TextField("Apellidos", text: $apellidos)
                TextField("Correo", text: $correo)
                    .keyboardType(.emailAddress)
            }
            .navigationTitle("Registrar huésped")
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Cancelar", action: dismiss.callAsFunction) }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Guardar") {
                        onSave(Guest(dni: dni, telefono: telefono, nombres: nombres, apellidos: apellidos, correo: correo))
                        dismiss()
                    }
                }
            }
        }
    }
}

struct ReservationListView: View {
    @ObservedObject var store: HotelDataStore
    @State private var showingForm = false

    var body: some View {
        List {
            ForEach(store.reservations) { reservation in
                VStack(alignment: .leading, spacing: 4) {
                    Text(reservation.huesped.nombres + " " + reservation.huesped.apellidos)
                        .font(.headline)
                    Text("Hab. \(reservation.habitacion.numero) · \(reservation.fechaInicio.formatted(date: .abbreviated, time: .omitted)) → \(reservation.fechaFin.formatted(date: .abbreviated, time: .omitted))")
                        .foregroundStyle(.secondary)
                    Text("Total S/ \(reservation.total, specifier: "%.2f")")
                        .font(.subheadline)
                }
            }
        }
        .navigationTitle("Reservas")
        .toolbar { Button(action: { showingForm = true }) { Label("Nueva", systemImage: "plus") } }
        .sheet(isPresented: $showingForm) {
            ReservationForm(store: store) { reservation in
                store.registrar(reservation: reservation)
            }
        }
    }
}

struct ReservationForm: View {
    @Environment(\.dismiss) private var dismiss
    @ObservedObject var store: HotelDataStore
    @State private var selectedGuest: Guest?
    @State private var selectedRoom: Room?
    @State private var selectedServices: Set<AdditionalService> = []
    @State private var fechaInicio = Date()
    @State private var fechaFin = Calendar.current.date(byAdding: .day, value: 2, to: Date()) ?? Date()

    var onSave: (Reservation) -> Void

    var body: some View {
        NavigationStack {
            Form {
                Picker("Huésped", selection: $selectedGuest) {
                    ForEach(store.guests) { guest in
                        Text("\(guest.nombres) \(guest.apellidos)").tag(Optional(guest))
                    }
                }

                Picker("Habitación", selection: $selectedRoom) {
                    ForEach(store.rooms.filter { $0.estado == .disponible }) { room in
                        Text("\(room.numero) · \(room.tipo.rawValue)").tag(Optional(room))
                    }
                }

                Section("Fechas") {
                    DatePicker("Inicio", selection: $fechaInicio, displayedComponents: .date)
                    DatePicker("Fin", selection: $fechaFin, in: fechaInicio..., displayedComponents: .date)
                }

                Section("Servicios adicionales") {
                    ForEach(store.services) { service in
                        MultipleSelectionRow(title: service.nombre, isSelected: selectedServices.contains(service)) {
                            if selectedServices.contains(service) {
                                selectedServices.remove(service)
                            } else {
                                selectedServices.insert(service)
                            }
                        }
                        .badge(String(format: "S/ %.2f", service.precio))
                    }
                }
            }
            .navigationTitle("Nueva reservación")
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Cancelar", action: dismiss.callAsFunction) }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Guardar") {
                        guard let selectedGuest, let selectedRoom else { return }
                        let reservation = Reservation(
                            huesped: selectedGuest,
                            habitacion: selectedRoom,
                            servicios: Array(selectedServices),
                            fechaInicio: fechaInicio,
                            fechaFin: fechaFin,
                            checkIn: false
                        )
                        onSave(reservation)
                        dismiss()
                    }
                }
            }
        }
    }
}

struct MultipleSelectionRow: View {
    let title: String
    let isSelected: Bool
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            HStack {
                Text(title)
                Spacer()
                if isSelected { Image(systemName: "checkmark.circle.fill").foregroundColor(.accentColor) }
            }
        }
    }
}

struct ReportsView: View {
    @ObservedObject var store: HotelDataStore

    var body: some View {
        List {
            Section("Ocupación") {
                let total = store.rooms.count
                let ocupadas = store.rooms.filter { $0.estado == .ocupada }.count
                let disponibles = store.rooms.filter { $0.estado == .disponible }.count
                ProgressView(value: Double(ocupadas), total: Double(max(1, total)))
                Text("Disponibles: \(disponibles) · Ocupadas: \(ocupadas)")
            }

            Section("Ingresos estimados") {
                let habitaciones = store.reservations.reduce(0) { $0 + $1.habitacion.precioPorNoche * Double(max(1, $1.noches)) }
                let extras = store.reservations.reduce(0) { total, reserva in
                    total + reserva.servicios.reduce(0) { $0 + $1.precio }
                }
                Label(String(format: "Habitaciones: S/ %.2f", habitaciones), systemImage: "bed.double.fill")
                Label(String(format: "Servicios: S/ %.2f", extras), systemImage: "cart")
                Label(String(format: "Total: S/ %.2f", habitaciones + extras), systemImage: "banknote")
            }
        }
        .navigationTitle("Reportes")
    }
}

// MARK: - Entry point
@main
struct HotelApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
