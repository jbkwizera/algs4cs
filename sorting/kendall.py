
def kendallTau(A, B):
    N = len(A)
    distance = 0
    for i in range(N):
        for j in range(i+1, N):
            a = A.index(i) - A.index(j)
            b = B.index(i) - B.index(j)
            if a * b < 0:
                distance += 1
    return distance


A = [0, 3, 1, 6, 2, 5, 4]
B = [1, 0, 3, 6, 4, 2, 5]

print(kendallTau(A, B))
