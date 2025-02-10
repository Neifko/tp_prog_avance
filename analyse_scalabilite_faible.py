import numpy as np
import matplotlib.pyplot as plt
from six import print_

# Fonction pour lire les données à partir d'un fichier
def read_data(file_path):
    with open(file_path, 'r') as file:
        # Lire les lignes et remplacer les virgules par des points
        data = [line.strip().replace(',', '.') for line in file if line.strip()]
    # Convertir les données en tableau NumPy
    return np.array([list(map(float, line.split())) for line in data])

# Fonction pour calculer le speedup
def calculate_speedup(data):
    # Extraire les colonnes
    temps_execution = data[:, 3]  # temps_ms
    nombre_process = data[:, 2]    # nombre_process

    # Calculer T1 (moyenne des temps d'exécution pour 1 processus)
    T1 = np.median(temps_execution[nombre_process == 1])

    # Calculer Tp (moyenne des temps d'exécution pour chaque nombre de processus)
    unique_processes = np.unique(nombre_process)
    Tp = []

    for p in unique_processes:
        Tp.append(np.median(temps_execution[nombre_process == p]))

    # Calculer Sp (speedup pour chaque nombre de processus)
    Sp = T1 / np.array(Tp)

    return Sp, unique_processes

# Fonction pour tracer le graphique
def plot_speedup(speedup_data, nombre_process_data, ntot_values):
    plt.figure(figsize=(10, 6))

    for speedup, nombre_process, ntot in zip(speedup_data, nombre_process_data, ntot_values):
        plt.plot(nombre_process, speedup, marker='o', label=f'ntot = {ntot}')

    plt.title('Speedup en fonction du nombre de processus')
    plt.xlabel('Nombre de processus')
    plt.ylabel('Speedup')
    plt.axhline(1, color='red', linestyle='--', label='Speedup = 1')

    plt.legend()
    plt.grid()

    plt.xlim(left=0)
    plt.ylim(bottom=0, top=2)  # Pour ne pas avoir de valeurs négatives sur l'axe des ordonnées
    plt.gca().set_aspect('equal', adjustable='box')

    plt.show()

# Main
if __name__ == "__main__":
    file_paths = [
        'out_ass102_g26_4c_faible_120000.txt',
        'out_ass102_g26_4c_faible_1200000.txt',
        'out_ass102_g26_4c_faible_12000000.txt'
    ]  # Liste des chemins de fichiers

    file_paths = [
        'out_pimw_g26_4c_faible_15000000.txt',
        'out_pimw_g26_4c_faible_25000000.txt',
        'out_pimw_g26_4c_faible_12000000.txt'
    ]  # Liste des chemins de fichiers

    # Initialiser des listes pour stocker les résultats
    speedup_data = []
    nombre_process_data = []
    ntot_values = []

    # Lire et traiter chaque fichier
    for file_path in file_paths:
        # Lire toutes les données
        data = read_data(file_path)

        # Filtrer les données pour récupérer uniquement celles avec 1 cœur (colonne 3, index 2)
        filtered_data_1_core = data[data[:, 2] == 1]  # Supposons que la troisième colonne contient le nombre de cœurs

        ntot_value = np.unique(filtered_data_1_core[:, 1])[0]  # Supposons que la deuxième colonne contient ntot

        # Calculer le speedup pour les données filtrées
        speedup, nombre_process = calculate_speedup(data)

        # Ajouter les résultats à la liste
        speedup_data.append(speedup)
        nombre_process_data.append(nombre_process)
        ntot_values.append(ntot_value)

    # Tracer les courbes pour chaque nombre total de fléchettes
    plot_speedup(speedup_data, nombre_process_data, ntot_values)
