import numpy as np dd

a=np.array([0,1])
b=np.array([-1,np.sqrt(3)])

a_dot_b=np.dot(a,b)
a_mul_b=np.linalg.norm(a) *np.linalg.norm(b)
cos_theta=a_dot_b/a_mul_b
theta=np/arccos(cos_theta)
theta_degree=theta/np.pi*180

print(a_dot_b)
print(a_mul_b)
print(cos_theta)
print(theta)
print(theta_degree)