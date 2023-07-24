![visitor badge](https://vbr.wocr.tk/badge?page_id=isaaclo97.Analyzing-the-influence-of-users-in-social-networks&color=be54c6&style=flat&logo=Github)
![Manintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GitHub last commit (master)](https://img.shields.io/github/last-commit/isaaclo97/Analyzing-the-influence-of-users-in-social-networks)
![Starts](https://img.shields.io/github/stars/isaaclo97/Analyzing-the-influence-of-users-in-social-networks.svg)

# A quick GRASP-based method for influence maximization in social networks.

The evolution and spread of social networks have attracted the interest of the scientific community in the last few years. Specifically, several new interesting problems, which are hard to solve, have arisen in the context of viral marketing, disease analysis, and influence analysis, among others. Companies and researchers try to find the elements that maximize profit, stop pandemics, etc. This family of problems is collected under the term Social Network Influence Maximization problem (SNIMP), whose goal is to find the most influential users (commonly known as seeds) in a social network, simulating an influence diffusion model. SNIMP is known to be an NP-hard problem and, therefore, an exact algorithm is not suitable for solving it optimally in reasonable computing time. The main drawback of this optimization problem lies on the computational effort required to evaluate a solution. Since each node is infected with a certain probability, the objective function value must be calculated through a Monte Carlo simulation, resulting in a computationally complex process. The current proposal tries to overcome this limitation by considering a metaheuristic algorithm based on the Greedy Randomized Adaptive Search Procedure (GRASP) framework to design a quick solution procedure for the SNIMP. Our method consists of two distinct stages: construction and local search. The former is based on static features of the network, which notably increases its efficiency since it does not require to perform any simulation during construction. The latter involves a local search based on an intelligent neighborhood exploration strategy to find the most influential users based on swap moves, also aiming for an efficient processing. Experiments performed on 7 well-known social network datasets with 5 different seed set sizes confirm that the proposed algorithm is able to provide competitive results in terms of quality and computing time when comparing it with the best algorithms found in the state of the art.

* Paper link: [https://doi.org/10.1007/s12652-021-03510-4](https://doi.org/10.1007/s12652-021-03510-4)
* Impact Factor: 3.662 
* Quartil: Q2 - 68/145 - Computer Science, Artificial Intelligence | Q2 - 73/164 - Computer Science, Information Systems | 2021  <br>
* Journal of Ambient Intelligence and Humanized Computing

## Datasets

* [CA-AstroPh](https://snap.stanford.edu/data/ca-AstroPh.html)
* [CA-CondMat](https://snap.stanford.edu/data/ca-CondMat.html)
* [Cit-HepPh](https://snap.stanford.edu/data/cit-HepPh.html)
* [Email-Enron](https://snap.stanford.edu/data/email-Enron.html)
* [Email-EuAll](https://snap.stanford.edu/data/email-EuAll.html)
* [p2p-Gnutella31](https://snap.stanford.edu/data/p2p-Gnutella31.html)
* [Wiki-Vote](https://snap.stanford.edu/data/wiki-Vote.html)

All txt format instances can be found in instances folder.

## Executable

You can just run the Surrogate-GRASP-SNIMP.jar as follows.

```
java -jar Surrogate-GRASP-SNIMP.jar
```

If you want new instances just replace folder instances.
Solution folder contains an excel with the results.

## Cite

Please cite our paper if you use it in your own work:

Bibtext
```
@article{LozanoOsorio2021,
  doi = {10.1007/s12652-021-03510-4},
  url = {https://doi.org/10.1007/s12652-021-03510-4},
  year = {2021},
  month = sep,
  publisher = {Springer Science and Business Media {LLC}},
  author = {Isaac Lozano-Osorio and Jes{\'{u}}s S{\'{a}}nchez-Oro and Abraham Duarte and {\'{O}}scar Cord{\'{o}}n},
  title = {A quick {GRASP}-based method for influence maximization in social networks},
  journal = {Journal of Ambient Intelligence and Humanized Computing}
}
```

MDPI and ACS Style
```
Lozano-Osorio, I.; Sánchez-Oro, J.; Duarte, A.; Cordón, Ó. A Quick GRASP-Based Method for Influence Maximization in Social Networks. Journal of Ambient Intelligence and Humanized Computing 2021. https://doi.org/10.1007/s12652-021-03510-4.
```

AMA Style
```
Lozano-Osorio I, Sánchez-Oro J, Duarte A, Cordón Ó. A quick GRASP-based method for influence maximization in social networks. J Ambient Intell Human Comput. 2021.
```

Chicago/Turabian Style
```
Lozano-Osorio, Isaac, Jesús Sánchez-Oro, Abraham Duarte, and Óscar Cordón. “A Quick GRASP-Based Method for Influence Maximization in Social Networks.” Journal of Ambient Intelligence and Humanized Computing, September 30, 2021. https://doi.org/10.1007/s12652-021-03510-4.
```
