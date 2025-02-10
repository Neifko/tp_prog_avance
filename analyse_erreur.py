import pandas as pd
import matplotlib.pyplot as plt
import glob

# Fonction pour lire les fichiers et extraire les données
def lire_fichiers(liste_fichiers):
    data = []
    for fichier in liste_fichiers:
        df = pd.read_csv(fichier, sep=r'\s+', header=None, names=['erreur', 'n_tot', 'n_proc', 'temps'])
        # Remplacer les virgules par des points et convertir en float
        df['erreur'] = df['erreur'].str.replace(',', '.').astype(float)
        # Ajouter une colonne pour indiquer la source des données
        if 'ass102' in fichier:
            df['source'] = 'ass102'
        elif 'pimw' in fichier:
            df['source'] = 'pimw'
        else:
            df['source'] = 'autre'
        data.append(df)
    return pd.concat(data, ignore_index=True)

# Fonction pour tracer les graphiques
def tracer_graphiques(df):
    fig, axes = plt.subplots(1, 2, figsize=(14, 6))

    # Tracer les nuages de points pour chaque source avec des couleurs différentes
    for source in df['source'].unique():
        subset = df[df['source'] == source]
        if source == 'ass102':
            color = 'blue'
        elif source == 'pimw':
            color = 'green'
        else:
            color = 'red'
        axes[0].scatter(subset['n_tot'], subset['erreur'], alpha=0.5, label=f'Données {source}', color=color)

        # Calculer la médiane pour chaque valeur de n_tot pour cette source
        medianes = subset.groupby('n_tot')['erreur'].median()

        # Tracer la médiane en courbes foncées avec des points
        if source == 'ass102':
            median_color = 'darkblue'
        elif source == 'pimw':
            median_color = 'darkgreen'
        else:
            median_color = 'darkred'
        axes[1].plot(medianes.index, medianes.values, color=median_color, label=f'Médiane {source}', zorder=5)
        axes[1].scatter(medianes.index, medianes.values, color=median_color, zorder=5)

    # Configurer les axes
    for ax in axes:
        ax.set_xscale('log')  # Échelle logarithmique pour l'axe des abscisses
        ax.set_yscale('log')  # Échelle logarithmique pour l'axe des ordonnées
        ax.set_xlabel('n_tot')
        ax.set_ylabel('Erreur')
        ax.grid(True)
        ax.legend()  # Ajouter une légende pour différencier les données et la médiane

    axes[0].set_title('Nuage de points : Erreur en fonction de n_tot')
    axes[1].set_title('Courbes de médiane : Erreur en fonction de n_tot')

    plt.tight_layout()
    plt.show()

if __name__ == '__main__':
    # Chemin vers les fichiers txt
    chemin_fichiers = 'out_*.txt'
    liste_fichiers = glob.glob(chemin_fichiers)

    # Lire les fichiers
    df = lire_fichiers(liste_fichiers)

    # Tracer les graphiques
    tracer_graphiques(df)
