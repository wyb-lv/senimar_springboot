# Project Endpoints and HTML Template Mapping

## Overview
This document maps all controllers, endpoints, and their corresponding HTML templates.

---

## 1. HomeController

### Public Endpoints
| Method | Endpoint | Template | Description |
|--------|----------|----------|-------------|
| GET | `/` | `index.html` | Home page with all products |
| GET | `/search` | `searchResults.html` | Search products by query |
| GET | `/product/{id}` | `productDetails.html` | Product details page |
| GET | `/products/category/{categoryId}` | `productsByCategory.html` | Products by category |
| GET | `/about` | `about.html` | About page |
| GET | `/contact` | `contact.html` | Contact page |
| GET | `/403` | `403.html` | Access denied error page |
| GET | `/404` | `404.html` | Not found error page |

### Admin Endpoints
| Method | Endpoint | Template | Description |
|--------|----------|----------|-------------|
| GET | `/admin/Dashboard` | `adminHome.html` | Admin dashboard with statistics |
| GET | `/account/home` | `userHome.html` | User home page |

---

## 2. CategoryController (`/admin`)

| Method | Endpoint | Template | Description | Form Fields |
|--------|----------|----------|-------------|-------------|
| GET | `/admin/categories` | `categories.html` | List all categories | - |
| POST | `/admin/categories` | Redirect to list | Add new category | `categoryname` |
| POST | `/admin/categories/update` | Redirect to list | Update category | `categoryid`, `categoryname` |
| GET | `/admin/categories/delete?id={id}` | Redirect to list | Delete category | `id` (query param) |
| GET | `/admin/categories/search?searchTerm={term}` | `categories.html` | Search categories | `searchTerm` (query param) |
| GET | `/admin/categories/{id}` | JSON response | Get category by ID (AJAX) | - |

---

## 3. ProductController (`/admin`)

| Method | Endpoint | Template | Description | Form Fields |
|--------|----------|----------|-------------|-------------|
| GET | `/admin/products` | `products.html` | List all products | - |
| GET | `/admin/products/add` | `productsAdd.html` | Show add product form | - |
| POST | `/admin/products/add` | Redirect to list | Add new product | `name`, `description`, `price`, `quantity`, `weight`, `productImage`, `categoryid` |
| GET | `/admin/products/update/{id}` | `productsUpdate.html` | Show update form | - |
| POST | `/admin/products/update` | Redirect to list | Update product | `id`, `name`, `description`, `price`, `quantity`, `weight`, `productImage`, `categoryid` |
| GET | `/admin/products/delete?id={id}` | Redirect to list | Delete product | `id` (query param) |
| GET | `/admin/products/search?searchTerm={term}` | `products.html` | Search products | `searchTerm` (query param) |

---

## 4. CustomerController (`/admin`)

| Method | Endpoint | Template | Description | Form Fields |
|--------|----------|----------|-------------|-------------|
| GET | `/admin/customers` | `displayCustomers.html` | List all customers | - |
| GET | `/admin/customers/delete?id={id}` | Redirect to list | Delete customer | `id` (query param) |
| GET | `/admin/customers/update/{id}` | `updateProfile.html` | Show update form | - |
| POST | `/admin/customers/update` | Redirect to list | Update customer | `id`, `username`, `email`, `address` |
| GET | `/admin/customers/search?searchTerm={term}` | `displayCustomers.html` | Search customers | `searchTerm` (query param) |
| GET | `/admin/customers/{id}` | JSON response | Get customer by ID (AJAX) | - |
| GET | `/admin/customers/stats` | JSON response | Get customer statistics (AJAX) | - |

---

## HTML Templates and Their Data Models

### Admin Templates

#### `adminHome.html`
- **Model Attributes:**
  - `totalProducts` (Long)
  - `totalCategories` (Long)
  - `totalCustomers` (Long)

#### `categories.html`
- **Model Attributes:**
  - `categories` (List<Category>)
  - `searchTerm` (String, optional)

#### `products.html`
- **Model Attributes:**
  - `products` (List<Product>)
  - `categories` (List<Category>)
  - `searchTerm` (String, optional)

#### `productsAdd.html`
- **Model Attributes:**
  - `categories` (List<Category>)

#### `productsUpdate.html`
- **Model Attributes:**
  - `product` (Product)
  - `categories` (List<Category>)

#### `displayCustomers.html`
- **Model Attributes:**
  - `customers` (List<Account>)
  - `searchTerm` (String, optional)

#### `updateProfile.html`
- **Model Attributes:**
  - `customer` (Account)

---

## Entity Relationships

### Account (ACCOUNT table)
- **Fields:** id, username, email, password, role, address
- **Relationships:** None

### Category
- **Fields:** id, name
- **Relationships:**
  - One-to-Many with Product

### Product
- **Fields:** id, name, description, price, quantity, weight, image
- **Relationships:**
  - Many-to-One with Category

---

## Default Values and Behaviors

1. **Product Image:** Defaults to `"Product Images/one.jpg"` if not provided
2. **Product Description:** Defaults to `"no product details"` if not provided

---

## Notes

- All admin endpoints are under `/admin` prefix
- Cart operations use `userId` parameter (should be replaced with session/security context in production)
- Delete operations use GET method (should be POST/DELETE in production for better security)
- Image uploads are handled as text URLs (file upload functionality can be added later)
- Authorization and login functionality are excluded as per requirements

