import Layout from '@/layout'

export default [
  {
    path: '/system/supplier',
    component: Layout,
    children: [
      {
        path: 'addSupplier',
        component: () =>
          import('@/views/system/supplier/addSupplierInfo'),
      }
    ],
  }

]
