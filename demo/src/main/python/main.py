import networkx as nx

def load_graph_from_file(filename):
    G = nx.DiGraph()  # Grafo dirigido
    with open(filename, 'r') as file:
        for line in file:
            parts = line.split()
            city1, city2, distance = parts[0], parts[1], int(parts[2])
            G.add_edge(city1, city2, weight=distance)
    return G

def find_shortest_path(G, origen, destino):
    try:
        shortest_path = nx.shortest_path(G, origen, destino, weight='weight')
        shortest_distance = nx.shortest_path_length(G, origen, destino, weight='weight')
        print(f"La ruta más corta de {origen} a {destino} es: {shortest_distance} KM")
        print("La ruta es:", " -> ".join(shortest_path))
    except nx.NetworkXNoPath:
        print(f"No hay ruta de {origen} a {destino}")

def find_center(G):
    try:
        center = nx.center(G)[0]
        print(f"El centro del grafo es: {center}")
    except nx.NetworkXError:
        print("El grafo está vacío")

def modify_graph(G):
    print("Opciones de modificación:")
    print("a) Interrupción de tráfico entre un par de ciudades")
    print("b) Establecer una nueva conexión entre dos ciudades")
    option = input("Seleccione una opción: ")

    if option == 'a':
        source = input("Ingrese la ciudad origen: ")
        destination = input("Ingrese la ciudad destino: ")
        G.remove_edge(source, destination)
    elif option == 'b':
        source = input("Ingrese la ciudad origen: ")
        destination = input("Ingrese la ciudad destino: ")
        distance = int(input("Ingrese la distancia en KM: "))
        G.add_edge(source, destination, weight=distance)
    else:
        print("Opción no válida")

def main():
    rutaArchivo = "demo\\src\\main\\java\\com\\example\\guategrafo.txt"
    G = load_graph_from_file(rutaArchivo)

    while True:
        print("\nOpciones:")
        print("1. Encontrar la ruta más corta entre dos ciudades")
        print("2. Encontrar el centro del grafo")
        print("3. Modificar el grafo")
        print("4. Finalizar el programa")

        option = input("Seleccione una opción: ")

        if option == '1':
            origen = input("Ingrese la ciudad origen: ")
            destino = input("Ingrese la ciudad destino: ")
            find_shortest_path(G, origen, destino)
        elif option == '2':
            find_center(G)
        elif option == '3':
            modify_graph(G)
        elif option == '4':
            break
        else:
            print("Opción no válida")

if __name__ == "__main__":
    main()
