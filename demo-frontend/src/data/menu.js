/*
 * Main and demo navigation arrays
 *
 * 'to' attribute points to the route name, not the path url
 */

export default {
  main: [
    {
      name: "儀表板",
      to: "backend-dashboard",
      icon: "si si-speedometer",
    },

    {
      name: "頁packs",
      icon: "si si-layers",
      subActivePaths: "/backend/pages-packs",
      sub: [
        {
          name: "後台頁面",
          icon: "si si-magnet",
          subActivePaths: "/backend-boxed",
          sub: [
            {
              name: "儀表板",
              to: "backend-boxed-dashboard",
            },
            {
              name: "搜尋",
              to: "backend-boxed-search",
            },
            {
              name: "Simple 1",
              to: "backend-boxed-simple1",
            },
            {
              name: "Simple 2",
              to: "backend-boxed-simple2",
            },
            {
              name: "Image 1",
              to: "backend-boxed-image1",
            },
            {
              name: "Image 2",
              to: "backend-boxed-image2",
            },
          ],
        },
      ],
    },
    {
      name: "愛蔬網後台頁面",
      heading: true,
    },
    {
      name: "會員管理",
      icon: "fa fa-users",
      subActivePaths: "/backend-boxed",
      sub: [
        {
          name: "儀表板",
          icon: "fa fa-chart-line",
          to: "backend-users-dashboard",
        },
        {
          name: "使用者管理",
          icon: "fa fa-user",
          to: "backend-users-user-info",
        },
      ],
    },
    // {
    //   name: "商家管理",
    //   icon: "fa fa-shop",
    //   subActivePaths: "/backend-boxed",
    //   sub: [
    //     {
    //       name: "儀表板",
    //       icon: "fa fa-chart-line",
    //       // to: "backend-boxed-dashboard",
    //     },
    //     {
    //       name: "商家後台",
    //       icon: "fa fa-home",
    //       // to: "backend-boxed-simple2",
    //     },
    //   ],
    // },
    {
      name: "餐廳管理",
      icon: "fa fa-burger",
      subActivePaths: "/backend-boxed",
      sub: [
        {
          name: "數據分析",
          icon: "fa fa-chart-column",
          to: "backend-restaurants-dashboard",
        },
        {
          name: "餐廳資訊",
          icon: "fa fa-store",
          to: "backend-restaurants-restaurant-info",
        },
      ],
    },
    {
      name: "網誌管理",
      icon: "fab fa-wpforms",
      subActivePaths: "/backend-boxed",
      sub: [
        {
          name: "儀表板",
          icon: "fa fa-chart-line",
          to: "backend-forums-dashboard",
        },
        {
          name: "文章管理",
          icon: "si si-book-open",
          to: "backend-forums-forums-info",
        },
      ],
    },
    {
      name: "食記管理",
      icon: "fa fa-pen-to-square",
      subActivePaths: "/backend-boxed",
      sub: [
        {
          name: "數據分析",
          icon: "fa fa-chart-pie",
          to: "backend-posts-dashboard",
        },
        {
          name: "文章管理",
          icon: "fa fa-file-pen",
          to: "backend-posts-posts-info",
        },
      ],
    },
    {
      name: "購物車管理",
      icon: "fa fa-cart-shopping",
      subActivePaths: "/backend-boxed",
      sub: [
        {
          name: "儀表板",
          icon: "fa fa-chart-line",
          to: "backend-cart-dashboard",
        },
        {
          name: "商品管理",
          icon: "fa fa-leaf",
          to: "backend-cart-product-info",
        },
        {
          name: "訂單管理",
          icon: "far fa-clipboard",
          to: "backend-cart-order-info",
        },
      ],
    },
    {
      name: "預訂管理",
      icon: "fa fa-clock",
      subActivePaths: "/backend-boxed",
      sub: [
        {
          name: "預訂分析",
          icon: "fa fa-hourglass",
          to: "backend-reserve-dashboard",
        },
        {
          name: "訂單總覽",
          icon: "fab fa-dashcube",
          to: "backend-reserve-info",
        },
      ],
    },
    {
      name: "使用者介面",
      heading: true,
    },
    {
      name: "板塊",
      icon: "si si-energy",
      subActivePaths: "/backend/blocks",
      sub: [
        {
          name: "風格Styles",
          to: "backend-blocks-styles",
        },
        {
          name: "選項Options",
          to: "backend-blocks-options",
        },
        {
          name: "表單Forms",
          to: "backend-blocks-forms",
        },
        {
          name: "主題Themed",
          to: "backend-blocks-themed",
        },
        {
          name: "板塊API",
          to: "backend-blocks-api",
        },
      ],
    },
    {
      name: "元素",
      icon: "si si-badge",
      subActivePaths: "/backend/elements",
      sub: [
        {
          name: "網格Grid",
          to: "backend-elements-grid",
        },
        {
          name: "排版Typography",
          to: "backend-elements-typography",
        },
        {
          name: "圖標Icons",
          to: "backend-elements-icons",
        },
        {
          name: "按鈕Buttons",
          to: "backend-elements-buttons",
        },
        {
          name: "按鈕套件Button Groups",
          to: "backend-elements-button-groups",
        },
        {
          name: "下拉式選單Dropdowns",
          to: "backend-elements-dropdowns",
        },
        {
          name: "標籤Tabs",
          to: "backend-elements-tabs",
        },
        {
          name: "導航Navigation",
          to: "backend-elements-navigation",
        },
        {
          name: "水平導航Horizontal Navigation",
          to: "backend-elements-navigation-horizontal",
        },
        {
          name: "多數據菜單Mega Menu",
          to: "backend-elements-mega-menu",
        },
        {
          name: "加載程式Progress",
          to: "backend-elements-progress",
        },
        {
          name: "提醒Alerts",
          to: "backend-elements-alerts",
        },
        {
          name: "工具提示Tooltips",
          to: "backend-elements-tooltips",
        },
        {
          name: "彈出提示框Popovers",
          to: "backend-elements-popovers",
        },
        {
          name: "模態Modals",
          to: "backend-elements-modals",
        },
        {
          name: "圖片延遲加載Images Overlay",
          to: "backend-elements-images-overlay",
        },
        {
          name: "時間軸Timeline",
          to: "backend-elements-timeline",
        },
        {
          name: "功能區Ribbons",
          to: "backend-elements-ribbons",
        },
        {
          name: "動畫Animations",
          to: "backend-elements-animations",
        },
        {
          name: "顏色主題Color Themes",
          to: "backend-elements-color-themes",
        },
      ],
    },
    {
      name: "Tables",
      icon: "si si-grid",
      subActivePaths: "/backend/tables",
      sub: [
        {
          name: "Styles",
          to: "backend-tables-styles",
        },
        {
          name: "Responsive",
          to: "backend-tables-responsive",
        },
        {
          name: "Helpers",
          to: "backend-tables-helpers",
        },
      ],
    },
    {
      name: "表單Forms",
      icon: "si si-note",
      subActivePaths: "/backend/forms",
      sub: [
        {
          name: "元素Elements",
          to: "backend-form-elements",
        },
        {
          name: "Layouts",
          to: "backend-form-layouts",
        },
        {
          name: "Input Groups",
          to: "backend-form-input-groups",
        },
        {
          name: "Plugins",
          to: "backend-form-plugins",
        },
        {
          name: "Editors",
          to: "backend-form-editors",
        },
        {
          name: "Validation",
          to: "backend-form-validation",
        },
      ],
    },
    {
      name: "開發者工具",
      heading: true,
    },
    {
      name: "Plugins",
      icon: "si si-wrench",
      subActivePaths: "/backend/plugins",
      sub: [
        {
          name: "Image Cropper",
          to: "backend-plugins-image-cropper",
        },
        {
          name: "Charts",
          to: "backend-plugins-charts",
        },
        {
          name: "Calendar",
          to: "backend-plugins-calendar",
        },
        {
          name: "Carousel",
          to: "backend-plugins-carousel",
        },
        {
          name: "Syntax Highlighting",
          to: "backend-plugins-syntax-highlighting",
        },
        {
          name: "Rating",
          to: "backend-plugins-rating",
        },
        {
          name: "Dialogs",
          to: "backend-plugins-dialogs",
        },
        {
          name: "Notifications",
          to: "backend-plugins-notifications",
        },
        {
          name: "Gallery",
          to: "backend-plugins-gallery",
        },
      ],
    },
    {
      name: "Layout",
      icon: "si si-magic-wand",
      subActivePaths: "/backend/layout",
      sub: [
        {
          name: "Page",
          subActivePaths: "/backend/layout/page",
          sub: [
            {
              name: "Default",
              to: "backend-layout-page-default",
            },
            {
              name: "Flipped",
              to: "backend-layout-page-flipped",
            },
          ],
        },
        {
          name: "Dark Mode",
          subActivePaths: "/backend/layout/dark-mode",
          sub: [
            {
              name: "On",
              to: "backend-layout-dark-mode-on",
            },
            {
              name: "Off",
              to: "backend-layout-dark-mode-off",
            },
            {
              name: "System",
              to: "backend-layout-dark-mode-system",
            },
          ],
        },
        {
          name: "Main Content",
          subActivePaths: "/backend/layout/main-content",
          sub: [
            {
              name: "Full Width",
              to: "backend-layout-main-content-full-width",
            },
            {
              name: "Narrow",
              to: "backend-layout-main-content-narrow",
            },
            {
              name: "Boxed",
              to: "backend-layout-main-content-boxed",
            },
          ],
        },
        {
          name: "Header",
          subActivePaths: "/backend/layout/header",
          sub: [
            {
              name: "Fixed",
              heading: true,
            },
            {
              name: "Light",
              to: "backend-layout-header-fixed-light",
            },
            {
              name: "Dark",
              to: "backend-layout-header-fixed-dark",
            },
            {
              name: "Static",
              heading: true,
            },
            {
              name: "Light",
              to: "backend-layout-header-static-light",
            },
            {
              name: "Dark",
              to: "backend-layout-header-static-dark",
            },
          ],
        },
        {
          name: "Sidebar",
          subActivePaths: "/backend/layout/sidebar",
          sub: [
            {
              name: "Mini",
              to: "backend-layout-sidebar-mini",
            },
            {
              name: "Light",
              to: "backend-layout-sidebar-light",
            },
            {
              name: "Dark",
              to: "backend-layout-sidebar-dark",
            },
            {
              name: "Hidden",
              to: "backend-layout-sidebar-hidden",
            },
          ],
        },
        {
          name: "Side Overlay",
          subActivePaths: "/backend/layout/side-overlay",
          sub: [
            {
              name: "Visible",
              to: "backend-layout-side-overlay-visible",
            },
            {
              name: "Hover Mode",
              to: "backend-layout-side-overlay-hover-mode",
            },
            {
              name: "No Page Overlay",
              to: "backend-layout-side-overlay-no-page-overlay",
            },
          ],
        },
        {
          name: "Loaders",
          to: "backend-layout-loaders",
        },
        {
          name: "API",
          to: "backend-layout-api",
        },
      ],
    },
    {
      name: "Multi Level Menu",
      icon: "si si-puzzle",
      sub: [
        {
          name: "Link 1-1",
          to: "#",
        },
        {
          name: "Link 1-2",
          to: "#",
        },
        {
          name: "Sub Level 2",
          sub: [
            {
              name: "Link 2-1",
              to: "#",
            },
            {
              name: "Link 2-2",
              to: "#",
            },
            {
              name: "Sub Level 3",
              sub: [
                {
                  name: "Link 4-1",
                  to: "#",
                },
                {
                  name: "Link 4-2",
                  to: "#",
                },
                {
                  name: "Sub Level 5",
                  sub: [
                    {
                      name: "Link 5-1",
                      to: "#",
                    },
                    {
                      name: "Link 5-2",
                      to: "#",
                    },
                    {
                      name: "Sub Level 6",
                      sub: [
                        {
                          name: "Link 6-1",
                          to: "#",
                        },
                        {
                          name: "Link 6-2",
                          to: "#",
                        },
                      ],
                    },
                  ],
                },
              ],
            },
          ],
        },
      ],
    },
    {
      name: "Pages",
      heading: true,
    },
    {
      name: "Generic",
      icon: "si si-layers",
      subActivePaths: "/backend/pages/generic",
      sub: [
        {
          name: "Blank",
          to: "backend-pages-generic-blank",
        },
        {
          name: "Blank (Block)",
          to: "backend-pages-generic-blank-block",
        },
        {
          name: "Search",
          to: "backend-pages-generic-search",
        },
        {
          name: "Profile",
          to: "backend-pages-generic-profile",
        },
        {
          name: "Invoice",
          to: "backend-pages-generic-invoice",
        },
        {
          name: "Pricing Plans",
          to: "backend-pages-generic-pricing-plans",
        },
        {
          name: "FAQ",
          to: "backend-pages-generic-faq",
        },
        {
          name: "Inbox",
          to: "backend-pages-generic-inbox",
        },
        {
          name: "Maintenance",
          to: "specials-maintenance",
        },
        {
          name: "Status",
          to: "specials-status",
        },
        {
          name: "Coming Soon",
          to: "specials-coming-soon",
        },
      ],
    },
    {
      name: "Authentication",
      icon: "si si-lock",
      subActivePaths: "/backend/pages/auth",
      sub: [
        {
          name: "All",
          to: "backend-pages-auth",
        },
        {
          name: "Sign In",
          to: "auth-signin",
        },
        {
          name: "Sign In 2",
          to: "auth-signin2",
        },
        {
          name: "Sign In 3",
          to: "auth-signin3",
        },
        {
          name: "Sign Up",
          to: "auth-signup",
        },
        {
          name: "Sign Up 2",
          to: "auth-signup2",
        },
        {
          name: "Sign Up 3",
          to: "auth-signup3",
        },
        {
          name: "Lock Screen",
          to: "auth-lock",
        },
        {
          name: "Lock Screen 2",
          to: "auth-lock2",
        },
        {
          name: "Lock Screen 3",
          to: "auth-lock3",
        },
        {
          name: "Pass Reminder",
          to: "auth-reminder",
        },
        {
          name: "Pass Reminder 2",
          to: "auth-reminder2",
        },
        {
          name: "Pass Reminder 3",
          to: "auth-reminder3",
        },
      ],
    },
    {
      name: "Error Pages",
      icon: "si si-fire",
      subActivePaths: "/backend/pages/errors",
      sub: [
        {
          name: "All",
          to: "backend-pages-errors",
        },
        {
          name: "400",
          to: "error-400",
        },
        {
          name: "401",
          to: "error-401",
        },
        {
          name: "403",
          to: "error-403",
        },
        {
          name: "404",
          to: "error-404",
        },
        {
          name: "500",
          to: "error-500",
        },
        {
          name: "503",
          to: "error-503",
        },
      ],
    },
  ],
  boxed: [
    {
      name: "Dashboard",
      to: "backend-boxed-dashboard",
      icon: "si si-compass",
    },
    {
      name: "Pages",
      heading: true,
    },
    {
      name: "Variations",
      icon: "si si-puzzle",
      sub: [
        {
          name: "Simple 1",
          to: "backend-boxed-simple1",
        },
        {
          name: "Simple 2",
          to: "backend-boxed-simple2",
        },
        {
          name: "Image 1",
          to: "backend-boxed-image1",
        },
        {
          name: "Image 2",
          to: "backend-boxed-image2",
        },
      ],
    },
    {
      name: "Search",
      to: "backend-boxed-search",
      icon: "si si-magnifier",
    },
    {
      name: "Go Back",
      to: "backend-dashboard",
      icon: "si si-action-undo",
    },
  ],
  demo: [
    {
      name: "回首頁",
      to: "index",
      icon: "fa fa-home",
      badge: 0,
    },
    {
      name: "Manage",
      heading: true,
    },
    {
      name: "購物車",
      icon: "fa fa-cart-shopping",
      to: "shoppingCart",
    },
    {
      name: "食記",
      icon: "fa fa-pen-to-square",
      to: "postIndex",
    },
    {
      name: "商家",
      icon: "fa fa-shop",
      sub: [
        {
          name: "店家加入",
          icon: "fa fa-plus",
          to: "BusinessRegister",
        },
      ],
    },
    {
      name: "Personal",
      heading: true,
    },
    {
      name: "Profile",
      icon: "far fa-user",
      sub: [
        {
          name: "Edit",
          to: "#",
        },
        {
          name: "Settings",
          to: "#",
        },
        {
          name: "Log out",
          to: "#",
        },
      ],
    },
  ],
};
